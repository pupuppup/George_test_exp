#!/usr/bin/env bash
# Script to stop emulator and Appium server
adb emu kill || true
pkill node || true
# TODO: Add more cleanup if needed 