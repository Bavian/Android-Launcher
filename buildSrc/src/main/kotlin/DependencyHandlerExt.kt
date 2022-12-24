import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementJetpackCompose() {
    arrayOf(
        Deps.AndroidX.Compose.ACTIVITY,
        Deps.AndroidX.Compose.UI,
        Deps.AndroidX.Compose.UI_TOOLING_PREVIEW,
        Deps.AndroidX.Compose.MATERIAL3,
    ).forEach {
        add("implementation", it)
    }

    add("debugImplementation", Deps.AndroidX.Compose.UI_TOOLING)
}