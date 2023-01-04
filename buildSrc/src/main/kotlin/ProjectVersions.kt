import org.gradle.api.JavaVersion

object ProjectVersions {

    const val GRADLE = "7.3.1"
    const val KOTLIN = "1.7.20"
    const val MATERIAL = "1.7.0"
    const val KOIN = "3.3.1"

    object Java {
        val VERSION = JavaVersion.VERSION_1_8
        const val JVM_TARGET = "1.8"
    }

    object Sdk {
        const val COMPILE_SDK = 33
        const val MIN_SDK = 21
        const val TARGET_SDK = COMPILE_SDK
    }

    object AndroidX {

        const val CORE_KTX = "1.9.0"
        const val LIFECYCLE_RUNTIME_KTX = "2.5.1"
        const val NAVIGATION_COMPOSE = "2.5.3"

        object Compose {
            const val COMPOSE = "1.3.2"
            const val KOTLIN_COMPILER_EXTENSION = COMPOSE
            const val UI = COMPOSE
            const val UI_TOOLING_PREVIEW = COMPOSE
            const val UI_TOOLING = COMPOSE

            const val ACTIVITY = "1.6.1"
            const val MATERIAL3 = "1.1.0-alpha03"
        }
    }
}