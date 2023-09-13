@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
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
