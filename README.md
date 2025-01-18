# Android App: Weather Hub

## Overview
Weather Hub is a modern Android application that provides real-time weather information and forecasts for any city. Designed with usability and performance in mind, the app uses state-of-the-art tools and frameworks to deliver a seamless and intuitive user experience. It adheres to Clean Architecture principles and employs MVVM and MVI design patterns for maintainability and scalability.

---

## Features

### City Weather Search
- **Purpose**: Allows users to search for and view the current weather for any city.
- **Details**:
  - Input a city name to retrieve real-time weather data.
  - Display the current temperature, and weather conditions (e.g., sunny, rainy, cloudy)

### 7-Day Weather Forecast
- **Purpose**: Provides a detailed 7-day weather forecast.
- **Details**:
  - Displays temperature and weather conditions for the next seven days in a clean and user-friendly list format.

### Dark Mode Support
- Fully compatible with dark mode for enhanced visual comfort.

---

## Technical Details

- **Framework**: Android Native Framework
- **Language**: Kotlin
- **API**: OpenWeatherMap API for real-time weather data
- **Architecture**: Clean Architecture
- **Design Patterns**:
  - MVVM (Model-View-ViewModel) for the city input screen.
  - MVI (Model-View-Intent) for the 7-day forecast screen.
- **UI Toolkit**: Jetpack Compose
- **State Management**: Flow (for reactive programming)
- **Dependency Injection**: Dagger-Hilt
- **Networking**: Retrofit with Gson and Kotlinx Serialization
- **Database**: DataStore Preferences (for lightweight data storage)
- **Custom Library**: Weather-utils (formats weather data)

---

## Project Structure

The project is modularized to promote separation of concerns and scalability:

- **App (main module)**: The application's entry point, coordinating overall app flow and dependencies.
- **Core**: Contains common utilities, shared components, and resources used across the app.
- **Data**: Manages data sources (network and local) and repository implementations.
- **Features**: Includes sub-modules for each feature or screen, such as:
  - **Today weather**: Handles user input for a city search, and displays current weather details.
  - **7-Day Forecast**: Shows a list of weather forecasts for the upcoming week.


---

## Installation

1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Open the project in Android Studio.
3. Sync project dependencies with Gradle:
   ```bash
   ./gradlew build
   ```
4. Build and run the application on an emulator or physical device.

---

## How It Works

1. **City Weather Search**:
   - Enter a city name in the input field.
   - Fetches current weather data from the OpenWeatherMap API and displays it, including temperature and condition with an icon.

2. **7-Day Forecast**:
   - Displays a scrollable list of weather forecasts for the next seven days, including temperature and weather conditions.

---

## Continuous Integration/Continuous Deployment (CI/CD)

A basic CI/CD pipeline is configured to ensure high code quality and smooth delivery:
- **Linting**: Ensures adherence to coding standards.
- **Unit Testing**: Verifies functionality using tools like JUnit, MockK, and Coroutines Test.
- **Build and Deployment**: Automatically builds the APK.

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.
