<p align="center">
  NammaMistri
</p>

> Your neighbourhood construction & home-service assistant — estimate, schedule, and manage work easily. 🛠️🏠

<p align="center">
  <a href="#overview"><img alt="NammaMistri" src="https://img.shields.io/badge/Android-Kotlin-4285F4.svg"/></a>
  <img alt="Build" src="https://img.shields.io/badge/build-gradle-brightgreen.svg"/>
  <img alt="Status" src="https://img.shields.io/badge/status-alpha-orange.svg"/>
  <img alt="License" src="https://img.shields.io/badge/license--unset-lightgrey.svg"/>
</p>



---

### About this app

NammaMistri is built to professionalize small-scale construction workflows and local home services. It packs material calculators, labor tracking, and photo-based progress logs into a rugged, offline-capable Android app so masons and homeowners can estimate, document, and settle work with confidence.

---

## Overview

NammaMistri is a lightweight Android app designed to help local masons and homeowners accurately estimate materials, track labor, and manage service appointments. It provides quick calculators for materials (bricks, cement, sand), a labor diary for attendance and advances, and tools to capture site photos and daily progress.

This repository contains the Android client; the project can optionally integrate with a backend server for authentication, bookings, push notifications, and persistent data storage.

---

## Problem Statement

Traditional construction estimation in rural and semi-urban areas is often manual, inconsistent, and error-prone. Mistris (local builders) commonly overestimate or underestimate quantities, causing material waste, project delays, and financial friction between builders and customers.

NammaMistri reduces waste and disputes by providing an on-device assistant that standardizes calculations, records labor activity, and preserves a clear audit trail for payments and progress.

---

## Core Innovation

- Coin-referenced & unit-aware calculators: standard civil-engineering formulas implemented with configurable wall thickness and unit settings.  
- Labor diary with automatic balance tracking: record attendance and advances; compute balances due per worker.  
- Site-first UX: large, rugged controls and camera-first workflows for field conditions.  
- Offline-first local persistence (Room DB) with sync-ready endpoints for optional backend integration.

---

## Technical Architecture

```
Android App (Kotlin)  <--->  REST / GraphQL API (optional backend)  <--->  Server DB
     - Jetpack (ViewModel, LiveData)
     - Room (local DB)
     - Retrofit / OkHttp
     - KSP for codegen
     - WorkManager for background sync
```

- Client: `app/` — Kotlin, Jetpack components, ViewModels, Room, Camera flows.  
- Backend: `backend/` (optional) — handles auth, bookings, notifications, and data persistence.  
- Data: local Room DB for sites, logs and cached assets; server DB for cross-device persistence.

---

## Workflow

1. Create or select a Site/Project and enter dimensions.  
2. Use the Material Calculator to estimate bricks, cement bags, and sand for walls and slabs.  
3. Record worker attendance and advances in the Labor Diary; the app calculates `Balance Due`.  
4. Capture Site Photos and tag them to tasks/dates for progress tracking.  
5. Schedule follow-ups or bookings and notify stakeholders (backend required).  
6. Export materials lists and simple invoices to share with customers or suppliers.

---

## Setup & Deployment

### Prerequisites

- Java JDK 11+  
- Android SDK + Platform Tools  
- Android Studio (recommended)  
- ADB on PATH

### Local build (Windows PowerShell):

```powershell
cd "d:\working projects\nammamistri app"
.\gradlew.bat assembleDebug
```

### Install on a connected device:

```powershell
adb install -r "app\build\outputs\apk\debug\app-debug.apk"
adb shell am start -n com.nammamistri.app/.ui.activities.MainActivity
```

### Run & debug with Android Studio

- Open the project and run on an emulator or device.  
- Use the debugger and Logcat to inspect runtime behavior.

### Optional backend deploy

- Deploy `backend/` to your hosting provider (Heroku, AWS, GCP, DigitalOcean).  
- Configure API base URL in the app build config or remote config.  
- Use HTTPS and secure token-based auth; enable FCM for push notifications.

---

## App Details (from project brief)

- Material Calculator: enter Length × Width × Height → get material list and approximate costs.  
- Labor Diary: record attendance, advances; app computes balance due per worker automatically.  
- UI: Tabbed layout (Calculator | Team | Photos), large buttons for on-site use.  
- Database: Room DB stores multiple active sites and logs; synchronize with backend when online.

---
