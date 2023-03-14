@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    alias(libs.plugins.anvil)
    id("com.adkhambek.android.library")
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {
    implementation(projects.core.shell)
    implementation(libs.dagger)

    implementation(libs.bundles.network)
    implementation(libs.jakewharton.timber)
    implementation(projects.dependencyInjection.core)

    debugApi(libs.plutolib.network.debug)
    releaseApi(libs.plutolib.network.release)

    debugApi(libs.plutolib.preferences.debug)
    releaseApi(libs.plutolib.preferences.release)
}
