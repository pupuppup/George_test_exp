# Test Plan: 24/7 Support Call Feature

## Scope
- 24/7 Support Call card on Communications screen
- Topic selection and dialer launch
- Analytics event logging
- Localisation (EN/UA/SK)
- Accessibility (TalkBack, contentDescription)
- Offline/online state handling

## Out of Scope
- Real call connection (ACTION_CALL)
- Authentication flow
- Real analytics backend

## Risks
- R1: Dialer intent fails on some devices
- R2: Debounce logic bypassed by edge cases
- R3: Localisation missing for new strings
- R4: Analytics events lost if app killed
- R5: Accessibility label not updated on locale change
- R6: Offline simulation not matching real offline
- R7: Emulator/device differences in dialer package
- R8: Appium selector flakiness
- R9: CI emulator instability

## Test Strategy
- Functional: All acceptance criteria
- Localisation: All supported locales
- Accessibility: Screen reader, contentDescription
- Negative: Offline, rapid taps, not logged in
- Analytics: Event order, debounce
- Reliability: Multiple runs, device matrix

## Environments / Data
- Android emulator API 33+
- Appium 2.x
- Test APK (debug)
- Locales: EN, UA, SK

## Entry Criteria
- App builds and installs
- Emulator/device available
- Appium server running

## Exit Criteria
- All critical test cases pass
- No regressions in core flows

## Test Cases

| ID           | GIVEN                | WHEN                        | THEN                                                        | ACs      |
|--------------|----------------------|-----------------------------|-------------------------------------------------------------|----------|
| TC-FUNC-01   | Online, logged in    | Open Communications         | Support 24/7 card visible                                   | AC1      |
| TC-FUNC-02   | Online, logged in    | Card visible                | Call button enabled                                         | AC2      |
| TC-FUNC-03   | Online, logged in    | Tap call, select topic      | System dialer opens with +421258268111                      | AC3      |
| TC-FUNC-04   | Online, logged in    | Tap call                    | No runtime permission dialog                                | AC4      |
| TC-FUNC-05   | Online, logged in    | Tap call rapidly (5x/1s)    | Only 1 dialer launch, 1 tap event                           | AC5      |
| TC-FUNC-06   | In dialer            | Return to app               | Communications screen state preserved                       | AC6      |
| TC-FUNC-07   | Any state            | Change locale               | All texts localised (EN/UA/SK)                              | AC7      |
| TC-FUNC-08   | Any state            | Use screen reader           | Call button has descriptive contentDescription              | AC8      |
| TC-FUNC-09   | Offline              | Open Communications         | Offline banner, call button disabled                        | AC9      |
| TC-FUNC-10   | Online, logged in    | Tap call                    | Analytics: tap, dialer_opened (once each, correct order)    | AC10     |

## Exploratory Charters
- Try switching offline/online rapidly
- Test with device in dark mode
- Try with TalkBack enabled
- Attempt to tap call while offline
- Change locale at runtime 