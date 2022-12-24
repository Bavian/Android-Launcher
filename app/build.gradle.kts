plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.bavian.androidlauncher"
    compileSdk = ProjectVersions.Sdk.COMPILE_SDK

    defaultConfig {
        applicationId = "com.bavian.androidlauncher"
        minSdk = ProjectVersions.Sdk.MIN_SDK
        targetSdk = ProjectVersions.Sdk.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = ProjectVersions.Java.VERSION
        targetCompatibility = ProjectVersions.Java.VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectVersions.Java.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectVersions.AndroidX.Compose.KOTLIN_COMPILER_EXTENSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:" + ProjectVersions.AndroidX.CORE_KTX)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:" + ProjectVersions.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation("com.google.android.material:material:" + ProjectVersions.MATERIAL)

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:" + ProjectVersions.AndroidX.Compose.ACTIVITY)
    implementation("androidx.compose.ui:ui:" + ProjectVersions.AndroidX.Compose.UI)
    implementation("androidx.compose.ui:ui-tooling-preview:" + ProjectVersions.AndroidX.Compose.UI_TOOLING_PREVIEW)
    implementation("androidx.compose.material3:material3:" + ProjectVersions.AndroidX.Compose.MATERIAL3)
    debugImplementation("androidx.compose.ui:ui-tooling:" + ProjectVersions.AndroidX.Compose.UI_TOOLING)

    // DI
    implementation("io.insert-koin:koin-android:" + ProjectVersions.KOIN)
}