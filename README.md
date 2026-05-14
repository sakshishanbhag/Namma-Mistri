# NammaMistri

> Your neighbourhood, your trusted technicians. Book home services instantly. 🛠️🏠

---

✨ NammaMistri helps people find, book, and manage local home-service professionals — plumbing, electrical, appliance repair, cleaning, carpentry, and more — in a few taps.

## Why NammaMistri? 🤝

- Fast: Minimal steps to request or book a service.
- Trustworthy: Technician profiles with ratings and verified work history.
- Transparent: Clear pricing, invoices, and service history.
- Reliable: Push updates and appointment reminders to keep things on track.

---

## Features 🚀

- Quick booking flow with category and sub-category selection.
- Schedule appointments and choose preferred time slots.
- Technician profiles, ratings, and customer reviews.
- Real-time status updates and push notifications.
- Service history, invoices, and appointment management.

## Tech Snapshot 🧰

- Platform: Android
- Language: Kotlin
- Build System: Gradle (Kotlin DSL)
- Architecture: Jetpack components, ViewModels, Room (inferred)
- Codegen: KSP used for annotation processing (inferred)

---

## Quick Start ⚡

Prerequisites: Android SDK, Java JDK, and ADB available in your PATH.

Build debug APK (Windows PowerShell):

```powershell
.\gradlew.bat assembleDebug
```

Install on a connected device:

```powershell
adb install -r "app\build\outputs\apk\debug\app-debug.apk"
```

Launch the app (example):

```powershell
adb shell am start -n com.nammamistri.app/.ui.activities.MainActivity
```

> Tip: Use Android Studio to run and debug on emulators or physical devices with instant deploy.

---



## Project Structure (high level)

- `app/` — Android application module
- `app/src/main/java` — Kotlin source code
- `app/build.gradle.kts` — Module build file
- `gradle/` — Gradle wrapper

---



## Roadmap 🔭

- Add in-app payments and secure wallets.
- Technician onboarding dashboard.
- Admin panel for service management.
- Multi-city and geofencing improvements.

---
