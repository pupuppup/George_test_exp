#!/usr/bin/env bash
# Script to build, install, and test the app on a local emulator
set -e

# Check Java
if ! command -v java &> /dev/null; then
  echo "Java not found!"; exit 1;
fi
# Check adb
if ! command -v adb &> /dev/null; then
  echo "adb not found!"; exit 1;
fi

# Create AVD if not exists
if ! avdmanager list avd | grep -q 'Name: qaApi33'; then
  echo "Creating AVD 'qaApi33'..."
  echo no | avdmanager create avd -n qaApi33 -k "system-images;android-33;google_apis;x86_64" --force
fi

# Start emulator if not running
if ! adb devices | grep -q emulator; then
  echo "Starting emulator..."
  emulator -avd qaApi33 -no-window -no-audio &
  # Wait for boot
  adb wait-for-device
  until [[ $(adb shell getprop sys.boot_completed 2>/dev/null | tr -d '\r') == "1" ]]; do
    sleep 2
  done
fi

# Build and install app
./gradlew :app:installDebug
APK_PATH=$(find app/build/outputs/apk/debug -name "app-debug.apk" | head -n1)
adb install -r "$APK_PATH"

# Start Appium if not running
if ! pgrep -f "appium" > /dev/null; then
  echo "Starting Appium..."
  appium --base-path /wd/hub &
  sleep 5
fi

# Run automation tests
./gradlew :qa-automation:test
RESULT=$?

if [ $RESULT -eq 0 ]; then
  echo "All tests passed."
else
  echo "Some tests failed. Exit code: $RESULT"
fi
exit $RESULT 