# NammaMistri

> Local home service booking — fast, reliable, and trusted.

NammaMistri is an Android app that connects users with nearby, vetted technicians for home repairs and services. Book jobs, schedule visits, track progress, and review service providers — all from your Android device.

## Features

- Fast booking flow for common home services (plumbing, electrical, appliances, carpentry, cleaning).
- Schedule appointments and receive reminders.
- Technician profiles with ratings and work history.
- Real-time status updates and notifications.
- Service history and invoice tracking.

## Tech Stack

- Platform: Android
- Language: Kotlin
- Build: Gradle (Kotlin DSL)
- Tooling: Jetpack libraries, Kotlin Symbol Processing (KSP) (inferred)

## Getting Started

Prerequisites: Android SDK, Java JDK, and ADB on your PATH.

Build a debug APK:

```powershell
./gradlew assembleDebug
```

Install the APK on a connected device:

```powershell
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Launch the app (example):

```powershell
adb shell am start -n com.nammamistri.app/.ui.activities.MainActivity
```

Note: On Windows you may want to run `gradlew.bat` instead of `./gradlew`.

## Contributing

- Open an issue for bugs or feature requests.
- Fork the repo, create a feature branch, and submit a pull request.
- Follow existing Kotlin/Android code style and include tests where applicable.

## License

Add a `LICENSE` file to this repository with your preferred license (for example, MIT or Apache-2.0).

## Contact

For questions or support, open an issue or add a pull request with proposed changes.
