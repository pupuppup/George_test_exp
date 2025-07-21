# George Support Call Demo

## Prehľad

Tento repozitár demonštruje vysoko kvalitný prístup k dodávke mobilných funkcií a QA automatizácii pre reálny scenár bankovej aplikácie:
**"24/7 Podpora volania"** — funkcia umožňujúca autentifikovaným používateľom rýchlo iniciovať podporu z obrazovky Komunikácie.

- **Technológie:** Kotlin, Jetpack Compose, Appium 2, JUnit5, GitHub Actions
- **QA:** Manuálny dizajn testov, sledovateľnosť, analytika, prístupnosť a robustná automatizácia
- **Lokalizácia:** Angličtina, Ukrajinčina, Slovenčina

---

## Štruktúra repozitára

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

## Architektúra

- **app/**: Android aplikácia (Jetpack Compose, single-activity, modulárna, lokalizovaná)
- **qa-automation/**: Automatizácia založená na Appium 2 (Kotlin, JUnit5, Page Object)
- **docs/**: Testovací plán, kritériá prijatia, analytická schéma, sledovateľnosť
- **.github/workflows/**: CI pipeline (build, emulátor, Appium, testy)
- **scripts/**: Pomocné skripty pre lokálne nastavenie

---

## Ako spustiť

### Predpoklady

- JDK 17+
- Android SDK (API 33+)
- Node.js (pre Appium)
- [Voliteľné] Allure CLI (pre bohaté reporty)

### 1. Zostavenie a spustenie aplikácie

```sh
./gradlew :app:installDebug
adb shell am start -n com.example.supportcall/.MainActivity
```

### 2. Spustenie automatizácie lokálne

```sh
# Spustenie emulátora a Appium, potom spustenie testov:
./scripts/run_local_env.sh
```

Alebo manuálne:

```sh
# Spustenie emulátora (ak nebeží)
emulator -avd qaApi33 &

# Spustenie Appium servera
appium --base-path /wd/hub &

# Spustenie testov
./gradlew :qa-automation:test
```

### 3. CI

- Pozri `.github/workflows/android-ui-tests.yml` pre kompletný pipeline.
- JUnit XML reporty sú nahrávané ako artefakty.
- Allure reportovanie je predkonfigurované (zakomentované).

---

## Automatizačné testovacie prípady

| ID             | Účel                                                      |
|----------------|------------------------------------------------------------|
| CallDialerTest | Otvorí volač s správnym číslom, kontroluje package       |
| DebounceTest   | Overuje debounce: 5 rýchlych tapov = 1 spustenie volača/event |
| OfflineStateTest| Kontroluje offline deaktivuje tlačidlo, zobrazí banner   |
| LocalisationTest| Overuje text tlačidla v UA/EN/SK                          |
| AnalyticsEventTest| Kontroluje poradie a počet analytických eventov         |

---

## Screenshoty

> **Poznámka:** Reálne screenshoty nie sú zahrnuté.  
> Vložte svoje vlastné do:
- `docs/images/communications_screen.png`
- `docs/images/topic_selection.png`
- `docs/images/dialer.png`

---

## Indikátory kvality

Pozri `docs/quality-metrics.md` pre kompletnú tabuľku.

---

## Ukrajinské zhrnutie / Українсьke резюме

Цей репозиторій демонструє повний цикл розробки та тестування мобільної фічі "24/7 Підтримка" для банківського додатку:
- Локалізація (EN/UA/SK)
- Автоматизація тестування (Appium + Kotlin)
- Високоякісний тест-план, критерії приймання, трасування вимог

---

## Ďalšie vylepšenia

- Rozšíriť na ACTION_CALL scenár (s runtime permission handling)
- Pridať negatívne/edge-case analytiku (napr. support_call_failed)
- Integrovať Allure reportovanie v CI (odkomentovať v workflow)
- Pridať viac lokalizácií (pozri `docs/localization-notes.md`)
- Pridať screenshot-based vizuálne regresné testy 