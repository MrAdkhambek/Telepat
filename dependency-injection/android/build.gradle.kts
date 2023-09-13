@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)
plugins {
    id("com.adkhambek.android.library")
}


dependencies {
    implementation(libs.dagger)
    api(projects.dependencyInjection.core)

    implementation(libs.coreKtx)
    implementation(libs.bundles.lifecycle)
}
