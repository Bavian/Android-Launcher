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

        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Deps.AndroidX.CORE_KTX)
    implementation(Deps.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Deps.MATERIAL)
    implementation(Deps.KOIN)
    implementJetpackCompose()

    implementation(project(":games-list"))
}