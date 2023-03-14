@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
}

dependencies {
    implementation(projects.core.shell)
    implementation(projects.core.res)
    api(libs.circleImageView)

    // DI
    implementation(projects.dependencyInjection.android)

    implementation(libs.bundles.androidx.ktx)
    implementation(libs.javax.inject)
    api(libs.input.mask)
}
