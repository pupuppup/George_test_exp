# Acceptance Criteria

- AC1: Support 24/7 card visible when online & logged in
- AC2: Call button enabled when online
- AC3: Tap opens system dialer with +421258268111 prefilled
- AC4: No runtime permission dialog appears
- AC5: Debounce: rapid taps (<=5 in 1s) produce exactly 1 dialer launch & 1 tap event
- AC6: Returning from dialer preserves screen state
- AC7: Localisation works for EN / UA / SK
- AC8: Accessibility: button has descriptive contentDescription
- AC9: Offline mode disables button & shows offline banner
- AC10: Analytics events `support_call_tap` then `support_call_dialer_opened` are logged exactly once per user action 