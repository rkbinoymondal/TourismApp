#  TourVista

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

##  Tech Stack & Libraries

* **Language:** Kotlin
* **Architecture:** MVVM (Model-View-ViewModel) Pattern
* **UI & Views:** ViewBinding, Material Components, BottomSheetDialogFragment, Facebook Shimmer Layout
* **Database & Networking:** Room Database, Retrofit 2, Gson
* **APIs & SDKs:** Google Generative AI Client SDK (`gemini-3-flash-preview`), Google Maps SDK
* **Utilities & Charts:** MPAndroidChart, Glide, Lottie Animations, Kotlin Coroutines

##  AI & LLM Usage Disclosure

* **Google Gemini API SDK Integration:** Took help to integrate the Google Gemini API SDK, as there wasn't much clear content on YouTube for connecting it in Android Studio—especially regarding the exact dependencies and Kotlin syntax.

* **MPAndroidChart (Pie Chart):** Used AI to understand the core concepts and parameters of the library (such as PieEntry, PieDataSet, and PieData), their dependencies, and how to properly implement them in the analytics screen.

* **Google Maps & Shared ViewModel:** While basic Google Maps integration was set up via Android Studio's built-in template(under google section), I used AI to learn and implement the Shared ViewModel logic to communicate place selection between the Tourism Fragment's RecyclerView and the Map Fragment.

* **Dashboard / Front Page UI:** The front page layout is largely AI-assisted, as my primary focus for this project was mainly on architecture, features, and functionality rather than designing UI from scratch due to time constraints also learned the Shimmer Effect Integration with startShimmer and stopshimmer functions(built the UI of shimmer myself, just how to start animation and stop took help).

* **RecyclerView Card Design:** Polished the XML design, layout weights, and styling of the destination cards with AI assistance.

* **BottomSheetDialog for Gemini Response:** Learned how to create and trigger the BottomSheetDialog to display the AI-generated responses cleanly.

* **BottomNavigationView with ViewPager2:** Took guidance from Naman Sir and AI to connect BottomNavigationView with ViewPager2 as I did it for the first time(as I had previously only used BottomNavigationView with standard FrameLayout containers).

* **Search & Filter Logic:** Learned the search/filtering logic using two mutable lists (original list vs. filtered list) and notifying adapter data changes. I debugged most issues myself, using AI only when hitting tricky edge cases.

* **Security & local.properties Configuration:** Learned how to read API keys safely from local.properties into Kotlin code via build.gradle.kts and BuildConfig so that sensitive Gemini and Google Maps API keys are never pushed to GitHub.

* **Core Logic & Architecture:** The rest of the project (Room database, MVVM structure, basic Fragments, and logic) was built by myself using knowledge gained from previous projects and lecture concepts, turning to AI mainly to learn and implement new libraries and features.
