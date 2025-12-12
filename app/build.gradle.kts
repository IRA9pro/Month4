plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.month4"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.month4"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // 1. Networking
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    // 2. Serialization  (JSON -> Kotlin)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.converter.kotlinx.serialization)

    // 3. Koin (DI)
    implementation(libs.koin.android)

    // 4. Coil (Images)
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)
}