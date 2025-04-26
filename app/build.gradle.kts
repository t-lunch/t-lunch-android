plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "ru.tinkoff.lunch"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.tinkoff.lunch"
        minSdk = 29
        targetSdk = 35
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Unit Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.cicerone)

    // Recycler
    implementation(libs.ti.recycler.coroutines)

    // Coil
    implementation(libs.coil)

    // Shimmers
    implementation(libs.shimmer)

    // Kotea
    implementation(libs.kotea.core)
    implementation(libs.kotea.android)

    // ViewBinding delegate
    implementation(libs.view.binding)

    // DI (Hilt)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Activity
    implementation(libs.activity)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.sandwich.retrofit)
    implementation(libs.lifecycle.runtime.ktx)

    // Data Store
    implementation(libs.datastore.preferences)

    // Android
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.swiperefreshlayout)
}
