# PokéBase ⚡
**A modern, high-performance Pokédex for Android**

PokéBase is a digital Pokédex that provides a rich, interactive experience for browsing Pokémon. Built with **Clean Architecture** and the latest **Android Jetpack** components, it demonstrates modern Android development practices, including reactive UI, robust networking, and smooth animations.

---

## 📸 Preview

<div align="center">
  <img src="screenshots/home.gif" width="300" alt="Home Screen">
  <br>
  <i>App Demo with smooth transitions and animations</i>
</div>

---

## ✨ Features
- **Offline-First Experience:** Uses a **Single Source of Truth (SSOT)** strategy. The UI observes a local database, ensuring instant access to data even without an internet connection.
- **Smart Caching:** Implements a cache expiration logic (24h timeout) to balance data freshness and network/battery efficiency.
- **Efficient Data Fetching:** Utilizes **GraphQL** via **Apollo Kotlin** to fetch only the necessary data from the [PokeAPI](https://pokeapi.co/), significantly improving performance over traditional REST.
- **Modern UI:** Built using **Jetpack Compose** for a declarative and responsive interface.
- **Fluid Animations:** Integrated **Lottie** animations for an engaging "Pokéball" loading experience.
- **Favorite System:** Bookmark your favorite Pokémon for quick access. The list is stored locally and updated in real-time using reactive Flow.
- **Clean Architecture:** Separated into Data, Domain, and Presentation layers for maximum maintainability and testability.

---

## 🛠 Tech Stack & Tools
- **Language:** Kotlin
- **UI:** Jetpack Compose & Material 3
- **Architecture:** MVVM + Clean Architecture (UseCases/Interactors)
- **Local Database:** **Room** (with Coroutines/Flow support)
- **Networking:** Apollo GraphQL 5, Retrofit 3 & OkHttp 5
- **Dependency Injection:** Hilt (using KSP for faster compilation)
- **Concurrency:** Kotlin Coroutines & Flow
- **Animations:** Lottie for Android
- **Build System:** Gradle 9.5.1 + Android Gradle Plugin 9.2.1

---

## 🏗 Architecture Overview
The project follows **Clean Architecture** principles to ensure separation of concerns:
- **Presentation:** UI components (Compose) and ViewModels using `StateFlow` to manage reactive UI states.
- **Domain:** Business logic defined in **UseCases** and repository interfaces (Pure Kotlin).
- **Data:** 
    - **Single Source of Truth:** Orchestrated by Repositories that sync Remote data (**Apollo/GraphQL**) into the Local database (**Room**).
    - **Mappers:** Transform Data entities and DTOs into clean Domain models.

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
