object Deps {

    const val GRADLE = "com.android.tools.build:gradle:" + ProjectVersions.GRADLE
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + ProjectVersions.KOTLIN
    const val MATERIAL = "com.google.android.material:material:" + ProjectVersions.MATERIAL
    const val KOIN = "io.insert-koin:koin-android:" + ProjectVersions.KOIN

    object AndroidX {

        const val CORE_KTX = "androidx.core:core-ktx:" + ProjectVersions.AndroidX.CORE_KTX
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:" + ProjectVersions.AndroidX.LIFECYCLE_RUNTIME_KTX

        object Compose {
            const val ACTIVITY = "androidx.activity:activity-compose:" + ProjectVersions.AndroidX.Compose.ACTIVITY
            const val UI = "androidx.compose.ui:ui:" + ProjectVersions.AndroidX.Compose.UI
            const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:" + ProjectVersions.AndroidX.Compose.UI_TOOLING_PREVIEW
            const val MATERIAL3 = "androidx.compose.material3:material3:" + ProjectVersions.AndroidX.Compose.MATERIAL3
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling:" + ProjectVersions.AndroidX.Compose.UI_TOOLING
        }
    }
}