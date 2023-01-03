buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(Deps.GRADLE)
        classpath(Deps.KOTLIN)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}