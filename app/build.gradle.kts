import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties();
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()){
    localProperties.load(FileInputStream(localPropertiesFile));
}
plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
    alias(libs.plugins.maps.secrets)
}

android {
    namespace = "com.example.tourismapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.tourismapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = localProperties.getProperty("Gemini_API_KEY")
        buildConfigField("String","Gemini_API_KEY","\"$apiKey\"")
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        buildConfig = true;
        viewBinding = true;
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    val room_version = "2.8.4"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("com.airbnb.android:lottie:6.7.1")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
}