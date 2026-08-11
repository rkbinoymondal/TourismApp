# 🌍 TourVista

**TourVista** is a native Android application built to provide travelers with interactive destination maps, visual data analytics, and real-time AI travel recommendations.

---

##  Key Features

*  **Interactive Maps:** Explore tourist spots and local attractions using integrated Google Maps SDK.
*  **AI Travel Assistant:** Instant destination summaries, highlights, best times to visit, and local cuisine guides powered by Google Gemini API.
*  **Shimmer Loading UI:** Skeleton shimmer animation (Facebook Shimmer) while fetching real-time AI responses for a smooth UX.
*  **Travel Analytics:** Visual charts and statistics powered by MPAndroidChart.
*  **Offline Caching:** Local data storage using Room Database for quick offline access.
*  **Modern Material Design:** Intuitive layout utilizing Bottom Navigation View, ViewPager2, TabLayout, and BottomSheet Dialogs.
*  **Secure Configuration:** Hidden API keys using Android `BuildConfig` and `local.properties`.

---

## 🛠️ Tech Stack & Libraries

* **Language:** Kotlin
* **Architecture:** MVVM (Model-View-ViewModel) Pattern
* **UI & Views:** ViewBinding, Material Components, BottomSheetDialogFragment, Facebook Shimmer Layout
* **Database & Networking:** Room Database, Retrofit 2, Gson
* **APIs & SDKs:** Google Generative AI Client SDK (`gemini-3-flash-preview`), Google Maps SDK
* **Utilities & Charts:** MPAndroidChart, Glide, Lottie Animations, Kotlin Coroutines
