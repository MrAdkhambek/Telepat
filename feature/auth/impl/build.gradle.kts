plugins {
    id("com.adkhambek.android.datasource.actual")
}

dependencies {
    implementation(projects.feature.auth.model)
    implementation(projects.feature.auth.api)

    implementation("com.thedeanda:lorem:2.1")
}