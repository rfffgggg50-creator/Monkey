# Monkey Hop Mobile Bot

A mobile-buildable Android project for a screen-based Monkey Hop auto-player.

## Build without Android Studio on your phone

Recommended: use GitHub Codespaces (browser) or GitHub Actions from your phone.

1. Create a GitHub repository and upload this project.
2. Open the repository in GitHub Codespaces, or use the included GitHub Actions workflow.
3. Run:
   `./gradlew assembleDebug`
4. The APK will be:
   `app/build/outputs/apk/debug/app-debug.apk`

## Phone permissions

The app uses Android MediaProjection for screen capture and AccessibilityService for touch gestures.
Android will show the required permission dialogs. The bot does not bypass Android security.

## How it works

The current implementation uses simple screen-color/shape heuristics and a configurable jump interval. Because games can change graphics, use the settings screen to tune the jump timing.

This is a starter project rather than a guaranteed game-specific AI. For a more accurate bot, replace the detector logic in `GameDetector.kt` with measurements from your exact game version.
