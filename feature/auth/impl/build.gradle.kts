plugins {
    id("com.adkhambek.android.datasource.actual")
}

dependencies {
    implementation(projects.feature.auth.model)
    implementation(projects.feature.auth.api)

    ksp(libs.dagger.compiler)

    implementation("com.thedeanda:lorem:2.1")
}