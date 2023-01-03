plugins {
    id("com.android.library")
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
}

dependencies {
    implementJetpackCompose()
    implementation(project(":apps-collector"))
    implementation(project(":ui:library"))
}