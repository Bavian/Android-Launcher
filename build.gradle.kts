buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:" + ProjectVersions.GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:" + ProjectVersions.KOTLIN)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}