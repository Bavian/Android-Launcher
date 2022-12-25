plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.bavian.games_list"
    compileSdk = ProjectVersions.Sdk.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectVersions.Sdk.MIN_SDK
        targetSdk = ProjectVersions.Sdk.TARGET_SDK
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
}

dependencies {
    implementJetpackCompose()
}