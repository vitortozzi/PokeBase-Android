# PokéBase ⚡
**A modern, high-performance Pokédex for Android**

PokéBase is a digital Pokédex that provides a rich, interactive experience for browsing Pokémon. Built with **Clean Architecture** and the latest **Android Jetpack** components, it demonstrates modern Android development practices, including reactive UI, robust networking, and smooth animations.

---

## 📸 Preview

<div align="center">
  <img src="screenshots/home.png" width="300" alt="Home Screen">
  <br>
  <i>Home Screen Overview</i>
</div>

### 🎬 Experience the App
Check out the app in action, featuring smooth Lottie animations and Jetpack Compose transitions:

[**Watch the Screen Recording**](screenshots/home_recording.mp4)

---

## ✨ Features
- **Efficient Data Fetching:** Utilizes **GraphQL** via **Apollo Kotlin** to fetch only the necessary data from the [PokeAPI](https://pokeapi.co/), reducing over-fetching and improving performance.
- **Modern UI:** Built using **Jetpack Compose** for a declarative and responsive interface.
- **Fluid Animations:** Integrated **Lottie** animations for an engaging "Pokéball" loading experience.
- **Clean Architecture:** Separated into Data, Domain, and Presentation layers for maximum maintainability and testability.
- **Image Caching:** Leverages **Coil 3** for efficient image loading and memory management.

---

## 🛠 Tech Stack & Tools
- **Language:** Kotlin (with Built-in Kotlin support in AGP 9)
- **UI:** Jetpack Compose & Material 3
- **Architecture:** MVVM + Clean Architecture (UseCases/Interactors)
- **Dependency Injection:** Hilt (using KSP for faster compilation)
- **Networking:** Apollo GraphQL 5, Retrofit 3 & OkHttp 5
- **Concurrency:** Kotlin Coroutines & Flow
- **Animations:** Lottie for Android
- **Build System:** Gradle 9.5.1 + Android Gradle Plugin 9.2.1

---

## 🏗 Architecture Overview
The project follows **Clean Architecture** principles to ensure separation of concerns:
- **Presentation:** UI components (Compose) and ViewModels using `StateFlow` to manage UI state.
- **Domain:** Business logic defined in **UseCases** and repository interfaces (Pure Kotlin).
- **Data:** Implementation of repositories, GraphQL services (Apollo), REST API (Retrofit), and Data Mappers.

---

## 🚀 Getting Started
1. Clone the repository.
2. Open in **Android Studio Iguana** or newer.
3. Ensure you have **JDK 17** configured.
4. Build and run:
   ```bash
   ./gradlew installDebug
   ```

---

## 📜 License
This project is licensed under the MIT License.
