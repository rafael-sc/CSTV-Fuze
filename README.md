# CSTV - Fuze.cc Code Challenge

## 1. Introduction

This document serves as a guide to the development and structure of the **CSTV** Android application project. This project was developed as a code challenge for Fuze.cc, showcasing modern Android development practices and clean architecture principles. The application provides users with an intuitive interface to browse CS:GO matches, view detailed match information, and explore team rosters with player details.

The project demonstrates proficiency in modern Android development using **Jetpack Compose**, **MVI architecture pattern**, **Clean Architecture principles**, and **dependency injection** with comprehensive testing coverage.

## 2. Key Features

### 2.1. Architecture

The project employs a **Clean Architecture with MVVM (Model-View-View-Model)** pattern, designed to ensure **scalability, maintainability, testability, and separation of concerns**.

**Clean Architecture Layers:**
- **Presentation Layer**: UI components, ViewModels, and state management
- **Domain Layer**: Business logic, use cases, and domain models
- **Data Layer**: Repository implementations, API clients, and data mapping


**Key Architectural Components:**
- **Modular Structure**: Separation into `app`, `domain`, `data`, and `core` modules
- **Dependency Injection**: Koin framework for clean dependency management
- **Repository Pattern**: Abstraction layer between data sources and business logic
- **Use Cases**: Encapsulation of business logic for specific operations
- **State Management**: StateFlow and Compose state integration

**Data Flow:**
1. User interaction triggers an Intent in the UI
2. ViewModel processes the Intent and calls appropriate Use Cases
3. Use Cases interact with Repositories to fetch/process data
4. Repository coordinates between remote APIs and local data sources
5. Data flows back through the layers, updating the UI State
6. Compose UI recomposes based on state changes

### 2.2. Modules with Description

The project is logically divided into the following modules, each with specific responsibilities:

**App Module (`app/`)**
- **Description**: Contains the presentation layer with UI screens, ViewModels, Compose components, navigation, themes, and dependency injection setup. This module orchestrates the user interface and handles user interactions.
- **Key Components**: MainActivity, Splash screen, Matches list screen, Match details screen, reusable UI components, navigation setup, and MVVM architecture.
- **Technologies**: Jetpack Compose, Navigation Compose, ViewModel, StateFlow, Koin DI
- **Dependencies**: domain, data, core modules

**Domain Module (`domain/`)**
- **Description**: Pure Kotlin module containing business logic, use cases, domain models, and repository interfaces. This layer is independent of Android framework and external dependencies, making it highly testable.
- **Key Components**: Match and Team domain models, MatchesUseCase, MatchDetailsUseCase, repository interfaces
- **Technologies**: Pure Kotlin, Kotlin Coroutines
- **Dependencies**: None (pure business logic)

**Data Module (`data/`)**
- **Description**: Handles data operations including network requests, data mapping, and repository implementations. Contains API definitions, DTOs, and data transformation logic.
- **Key Components**: Retrofit API interfaces (MatchesApi, TeamsApi), DTO classes, Repository implementations, data mappers, network configuration
- **Technologies**: Retrofit, Kotlin Serialization, OkHttp, Coroutines
- **Dependencies**: domain module

**Core Module (`core/`)**
- **Description**: Shared utilities and common interfaces used across multiple modules. Contains abstractions and utility classes that can be reused throughout the application.
- **Key Components**: ResourceProvider interface for string resources access
- **Technologies**: Android framework utilities
- **Dependencies**: None

### 2.3. Key Use Cases

This project addresses the following primary use cases, demonstrating comprehensive CS:GO match tracking functionality:

**Match Browsing**
- **Description**: Users can browse a list of upcoming and ongoing CS:GO matches with real-time status updates, team information, and league details.
- **User Flow**:
  1. User opens the app and sees splash screen
  2. App loads and displays matches list with pull-to-refresh capability
  3. Matches show team logos, names, match time, league information, and live status
  4. Users can scroll through paginated match results
  5. Real-time updates reflect match status changes

**Match Details Exploration**
- **Description**: Users can tap on any match to view comprehensive details including full team rosters, player information with avatars, match metadata, and league information.
- **User Flow**:
  1. User taps on a match card from the matches list
  2. App navigates to match details screen showing detailed match information
  3. Screen displays both teams' complete rosters with player names and avatars
  4. Users can view league information, match timing, and status
  5. Responsive design adapts to portrait and landscape orientations

**Responsive Design Experience**
- **Description**: Application provides optimized layouts for different screen orientations and sizes, ensuring consistent user experience across various Android devices.
- **User Flow**:
  1. Users can rotate their device between portrait and landscape modes
  2. App automatically adapts layout and component positioning
  3. Content remains accessible and properly formatted in all orientations

**Offline-First Data Management**
- **Description**: Efficient data loading with pull-to-refresh functionality and proper error handling for network issues.
- **User Flow**:
  1. App attempts to load fresh data on startup
  2. Users can pull down to refresh match data
  3. Proper loading states and error messages guide user experience
  4. Graceful handling of network connectivity issues

### 2.4. Libraries Used

The development leveraged carefully selected modern Android libraries to ensure robustness, performance, and maintainability:

**UI Framework & Components:**
- **Jetpack Compose BOM (2025.07.00)**: Modern declarative UI toolkit for building native Android UI
- **Material 3**: Google's latest design system implementation for consistent UI components
- **Material Icons Extended**: Comprehensive icon library for UI elements
- **Navigation Compose (2.9.2)**: Type-safe navigation between Compose screens
- **Activity Compose (1.10.1)**: Integration between traditional Android activities and Compose
- **Core Splashscreen (1.2.0-rc01)**: Modern splash screen implementation following Android 12+ guidelines

**Architecture & State Management:**
- **Lifecycle Runtime KTX (2.9.2)**: Lifecycle-aware components and state management
- **ViewModel**: MVVM architecture support with state persistence
- **StateFlow & Flow**: Reactive state management and asynchronous data streams
- **Kotlin Coroutines (1.10.2)**: Asynchronous programming and concurrent operations

**Dependency Injection:**
- **Koin BOM (4.1.0)**: Lightweight dependency injection framework for Kotlin
- **Koin Androidx Compose**: Compose-specific Koin integration for dependency injection in UI layer

**Networking & Data:**
- **Retrofit (3.0.0)**: Type-safe HTTP client for REST API communication
- **Retrofit Kotlin Serialization Converter (1.0.0)**: JSON serialization support for Retrofit
- **OkHttp Logging Interceptor (5.1.0)**: HTTP request/response logging for debugging
- **Kotlinx Serialization JSON (1.9.0)**: Kotlin-native JSON serialization library

**Image Loading:**
- **Coil Compose (3.2.0)**: Modern image loading library optimized for Jetpack Compose
- **Coil Network OkHttp (3.2.0)**: OkHttp integration for efficient image network requests

**Data Persistence:**
- **Room (2.7.2)**: SQLite database abstraction layer for local data storage
- **DataStore Preferences (1.1.7)**: Modern preference storage solution replacing SharedPreferences

**Testing Frameworks:**
- **JUnit 4 (4.13.2)**: Unit testing framework for business logic testing
- **MockK (1.13.8)**: Mocking library for Kotlin with powerful mocking capabilities
- **Kotlinx Coroutines Test (1.10.2)**: Testing utilities for coroutine-based code
- **Compose UI Test (1.8.3)**: UI testing framework specifically designed for Jetpack Compose
- **Robolectric (4.13)**: Android unit testing framework that runs on JVM
- **Koin Test JUnit5 (4.1.0)**: Testing utilities for Koin dependency injection

**Code Quality & Analysis:**
- **Detekt (1.23.6)**: Static code analysis tool for Kotlin code quality
- **KtLint (13.0.0)**: Kotlin code formatting and linting tool
- **Detekt Formatting**: Additional formatting rules for code consistency

**Build Tools & Processing:**
- **KSP (Kotlin Symbol Processing) (2.2.0-2.0.2)**: Code generation and annotation processing
- **Android Gradle Plugin (8.10.1)**: Build system for Android applications
- **Kotlin (2.2.0)**: Programming language with latest features and optimizations

**Firebase & Crash Reporting:**
- **Firebase BOM (33.16.0)**: Unified Firebase library versions management
- **Firebase Analytics**: User behavior tracking and app analytics
- **Firebase Crashlytics**: Real-time crash reporting and analysis
- **Firebase Auth**: Authentication services for user management

## 3. Project Structure

The project follows Clean Architecture principles with clear module separation:

```
CSTVFuzecc/
├── app/                    # Presentation Layer (UI, ViewModels, Navigation)
│   ├── src/main/java/com/orafaelsc/cstvfuze/
│   │   ├── ui/            # Compose screens and components
│   │   ├── di/            # Dependency injection modules
│   │   ├── util/          # UI utilities and extensions
│   │   └── CSTVFuzeApp.kt # Application class
│   └── src/test/          # UI and component tests
├── domain/                # Business Logic Layer
│   ├── src/main/java/com/orafaelsc/cstvfuze/domain/
│   │   ├── model/         # Domain entities
│   │   ├── repository/    # Repository interfaces
│   │   ├── usecase/       # Business logic use cases
│   │   └── di/            # Domain DI module
│   └── src/test/          # Use case unit tests
├── data/                  # Data Layer
│   ├── src/main/java/com/orafaelsc/cstvfuze/data/
│   │   ├── remote/        # API interfaces and DTOs
│   │   ├── repository/    # Repository implementations
│   │   └── di/            # Data and Network DI modules
│   └── src/test/          # Repository tests
├── core/                  # Shared utilities
└── gradle/                # Build configuration
```

## 4. Development Highlights

### 4.1. Clean Architecture Implementation
- **Separation of Concerns**: Each layer has distinct responsibilities with clear interfaces
- **Dependency Inversion**: Higher-level modules don't depend on lower-level modules
- **Testability**: Business logic is isolated and easily testable
- **Maintainability**: Changes in one layer don't affect others

### 4.2. Modern Android Development
- **100% Jetpack Compose**: No XML layouts, fully declarative UI
- **MVVM Pattern**: Predictable state management with unidirectional data flow
- **Kotlin Coroutines**: Asynchronous programming with structured concurrency
- **Type Safety**: Leveraging Kotlin's type system for compile-time safety

### 4.3. Comprehensive Testing Strategy
- **Unit Tests**: Use cases and business logic testing
- **Repository Tests**: Data layer testing with mocked dependencies
- **UI Component Tests**: Compose UI component testing

### 4.4. Performance Optimizations
- **Efficient Image Loading**: Coil for optimized image caching and loading
- **State Management**: Minimal recomposition with stable state objects
- **Memory Management**: Proper lifecycle handling and resource cleanup

## 5. Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17 or higher
- Android SDK API 35+
- Git

### Setup Instructions
1. Clone the repository: `git clone [repository-url]`
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Build and run the application on an emulator or physical device

### Testing
- Run unit tests: `./gradlew test`
- Run UI tests: `./gradlew connectedAndroidTest`
- Run code quality checks: `./gradlew detekt ktlintCheck`

## 6. Technical Decisions

### 6.1. Clean Architecture
- **Scalability**: Easy to add new features without affecting existing code
- **Testability**: Business logic can be tested independently
- **Maintainability**: Clear separation of concerns makes code easier to maintain

### 6.3 Jetpack Compose
- **Modern**: Latest UI toolkit from Google with active development
- **Declarative**: More intuitive and less error-prone than imperative UI
- **Performance**: Optimized rendering with smart recomposition

*This project demonstrates modern Android development practices and serves as a comprehensive example of clean, scalable, and maintainable mobile application architecture.*
