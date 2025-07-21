# George Support Call Demo

## Overview

This repository demonstrates a high-quality approach to mobile feature delivery and QA automation for a real-world banking app scenario:
**“24/7 Support Call”** — a feature allowing authenticated users to quickly initiate a support call from the Communications screen.

- **Tech:** Kotlin, Jetpack Compose, Appium 2, JUnit5, GitHub Actions
- **QA:** Manual test design, traceability, analytics, accessibility, and robust automation
- **Localization:** English, Slovak

---

## Repository Structure

```text
.
├─ README.md
├─ CONTRIBUTING.md
├─ gradle/
├─ gradlew
├─ gradlew.bat
├─ settings.gradle.kts
├─ build.gradle.kts
├─ app/
│  ├─ build.gradle.kts
│  ├─ src/
│  │  ├─ main/
│  │  │  ├─ AndroidManifest.xml
│  │  │  ├─ java/com/example/supportcall/
│  │  │  └─ res/
│  │  │     ├─ values/strings.xml
│  │  │     ├─ values-uk/strings.xml
│  │  │     ├─ values-sk/strings.xml
│  │  │     ├─ values/colors.xml
│  │  │     └─ mipmap-anydpi-v26/ic_launcher.xml
│  │  └─ debug/
│  │     └─ AndroidManifest.xml
├─ qa-automation/
│  ├─ build.gradle.kts
│  └─ src/test/kotlin/
│     ├─ config/DriverFactory.kt
│     ├─ pages/CommunicationsScreen.kt
│     ├─ pages/SupportCard.kt
│     ├─ util/Logcat.kt
│     ├─ util/LocaleSwitcher.kt
│     └─ tests/
│        ├─ CallDialerTest.kt
│        ├─ DebounceTest.kt
│        ├─ OfflineStateTest.kt
│        ├─ LocalisationTest.kt
│        └─ AnalyticsEventTest.kt
├─ docs/
│  ├─ acceptance-criteria.md
│  ├─ test-plan.md
│  ├─ traceability-matrix.md
│  ├─ analytics-event-schema.json
│  ├─ localization-notes.md
│  └─ images/
│     ├─ communications_screen.png
│     ├─ topic_selection.png
│     └─ dialer.png
├─ scripts/
│  ├─ run_local_env.sh
│  └─ kill_env.sh
├─ .github/
│  └─ workflows/
│     └─ android-ui-tests.yml
├─ .gitignore
└─ gradle.properties
```

---

## Architecture

- **app/**: Android app (Jetpack Compose, single-activity, modular, localized)
- **qa-automation/**: Appium 2-based automation (Kotlin, JUnit5, Page Object)
- **docs/**: Test plan, acceptance criteria, analytics schema, traceability
- **.github/workflows/**: CI pipeline (build, emulator, Appium, tests)
- **scripts/**: Helper scripts for local onboarding

---

## Prerequisites

- JDK 17+
- Android SDK (API 33+)
- Node.js (for Appium)
- [Optional] Allure CLI (for rich reports)

---

## How to Run

### 1. Build and Run the App

```sh
./gradlew :app:installDebug
adb shell am start -n com.example.supportcall/.MainActivity
```

### 2. Run Automation Locally

```sh
# Start emulator and Appium, then run tests:
./scripts/run_local_env.sh
```

Or manually:

```sh
# Start emulator (if not running)
emulator -avd qaApi33 &

# Start Appium server
appium --base-path /wd/hub &

# Run all tests
./gradlew :qa-automation:test

# Run smoke tests
./gradlew :qa-automation:test --tests "*Smoke*"
```

### 3. CI

- See `.github/workflows/android-ui-tests.yml` for the full pipeline.
- JUnit XML reports are uploaded as artifacts.
- Allure reporting is preconfigured (commented out).

---

## Automation Test Cases

| ID               | Purpose                                                        |
|------------------|----------------------------------------------------------------|
| CallDialerTest   | Opens dialer with correct number, checks package               |
| DebounceTest     | Verifies debounce: 5 rapid taps = 1 dialer launch/event        |
| OfflineStateTest | Checks offline disables button, shows banner                   |
| LocalisationTest | Verifies button text in EN/SK                                  |
| AnalyticsEventTest| Checks analytics event order and count                        |

---

## Screenshots

> **Note:** Real screenshots are not included.  
> Drop your own into:
- `docs/images/communications_screen.png`
- `docs/images/topic_selection.png`
- `docs/images/dialer.png`

---

## Quality Indicators

See `docs/quality-metrics.md` for the full table.

---

## Important for Automation (Appium + Jetpack Compose)

**Jetpack Compose does not add resource-id or text for Button elements with testTag!**

- Appium does not see testTag as a resource-id or text in the view hierarchy for Compose Button.
- The main call button is always found as the first `android.widget.Button` on the screen.
- Do NOT search for the button by text or content-desc. Always use `By.className("android.widget.Button")`.
- If you need to check the button's text, do it via unit tests or screenshot/OCR, not via Appium.

---

## How to Run Automation Tests (Appium)

1. **Build and install the app:**
   ```sh
   ./gradlew :app:installDebug
   adb shell am start -n com.example.supportcall/.MainActivity
   ```
2. **Start an emulator or connect a real device.**
3. **Start the Appium server:**
   ```sh
   appium --base-path /wd/hub &
   ```
4. **Run the automation tests:**
   ```sh
   ./gradlew :qa-automation:test
   ```
5. **View the reports:**
   - HTML report: `qa-automation/build/reports/tests/test/index.html`
   - XML report: `qa-automation/build/test-results/test/`

---

## How element search works in tests

- **The "Call/Zavolať" button** is searched by xpath using visible text.
- **Topic selection buttons** are also searched by xpath using visible text ("Klientske centrum", "Client centre").
- If you change the text in the UI, don't forget to update the locators in the tests!

--- 