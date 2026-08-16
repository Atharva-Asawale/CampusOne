# Phase 1 Implementation Plan: Campus One Foundation

This plan covers the initial setup of the Campus One Android application, focusing on architecture, dependency injection, navigation, and theme.

## User Review Required

> [!IMPORTANT]
> The project namespace will be **`com.campusone.app`**.

> [!IMPORTANT]
> **Minimalist Feature Structure**: I will NOT create the full `data`/`domain`/`presentation` sub-folders for every feature yet. These will be added incrementally as we implement each feature. For Phase 1, we will only create the feature-level packages with their initial placeholder screens.

> [!NOTE]
> **UI State Pattern**: We will establish the `UiState` + `ViewModel` + `Composable` pattern immediately. All UI logic will be driven by immutable state emitted from ViewModels.

## Proposed Changes

### 1. Project Configuration & Metadata
- Update namespace and applicationId to `com.campusone.app` in `app/build.gradle.kts`.
- Update `libs.versions.toml` with modern stable versions:
    - Compose (BOM), Hilt (2.51+), Navigation (2.8.0+ for Type-Safety), Retrofit, Room, DataStore, Coil, Kotlin Serialization.
- Configure `app/build.gradle.kts` with necessary plugins: `dagger.hilt.android`, `kotlin.serialization`, `com.google.devtools.ksp`.

### 2. Core Architecture Skeleton
- **`com.campusone.app.core`**:
    - `common`: `UserRole` enum, `UiState` base classes, etc.
    - `designsystem`: Material 3 `Theme`, `Color`, `Type`, `Dimensions`, and a `components` package for reusable UI (Buttons, Cards, TopBars).
    - `navigation`: Type-safe route definitions using `@Serializable`.
    - `network`: Retrofit/OkHttp configuration (Infrastructure only).
    - `database`: Room configuration (Infrastructure only, no entities yet).
    - `datastore`: DataStore configuration for preferences/auth state.

### 3. Feature Skeleton (Placeholders)
- **`com.campusone.app.feature`**:
    - `auth`: `LoginScreen`, `AuthViewModel`, `AuthUiState`.
    - `dashboard`: `StudentDashboardScreen`, `AdminDashboardScreen`.
    - `events`, `clubs`, `chat`, `placements`, `sports`, `profile`: Minimal placeholder screens showing titles.

### 4. Dependency Injection (Hilt)
- `CampusOneApplication` class.
- DI Modules for Network, Database, and DataStore infrastructure.

### 5. Navigation Flow
- `AppNavGraph` orchestrating:
    - `Splash` -> `Login`
    - `Login` -> `StudentDashboard` (with BottomBar) or `AdminDashboard`.
- Bottom Navigation routes: `Home`, `Events`, `Clubs`, `Chat`, `Profile`.

## Verification Plan

### Automated Tests
- `gradle build` to ensure project compiles with new namespace and dependencies.

### Manual Verification
- Deploy to device/emulator.
- Verify **Splash** transitions to **Login**.
- Verify **Login** allows selecting "Student" or "Admin".
- Verify **Student Dashboard** shows Bottom Navigation and placeholder content for each tab.
- Verify **Admin Dashboard** shows a distinct placeholder.
