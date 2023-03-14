@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)
plugins {
    id("com.adkhambek.android.library")
    alias(libs.plugins.anvil)
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {
    implementation(libs.dagger)
    implementation(libs.anvil.annotations)
    api(projects.dependencyInjection.core)

    implementation(libs.coreKtx)
    implementation(libs.bundles.lifecycle)
}
