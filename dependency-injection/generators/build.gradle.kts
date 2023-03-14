@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.ksp)
}

kotlin {
    explicitApi()
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-opt-in=com.squareup.anvil.annotations.ExperimentalAnvilApi"
    }
}

dependencies {
    implementation(projects.dependencyInjection.core)

    implementation(libs.dagger)
    implementation(libs.squareup.retrofit)

    implementation(libs.anvil.compiler.api)
    implementation(libs.anvil.compiler.utils)

    implementation(libs.kotlinpoet.core)
    ksp(libs.kotlinpoet.ksp)

    implementation(libs.auto.service.annotations)
    ksp(libs.auto.service.ksp)

    testImplementation(testFixtures(libs.anvil.compiler.utils))
    testImplementation(libs.junit4)
}
