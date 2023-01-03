plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.bavian.apps_collector"
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
    compileOptions {
        sourceCompatibility = ProjectVersions.Java.VERSION
        targetCompatibility = ProjectVersions.Java.VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectVersions.Java.JVM_TARGET
    }
}

dependencies {

}