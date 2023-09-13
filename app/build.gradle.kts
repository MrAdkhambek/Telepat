@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.adkhambek.android.application")
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
    id("org.jetbrains.kotlin.android")
}

android {
    val localProperties = gradleLocalProperties(rootDir)

    signingConfigs {
//        getByName(Config.DEBUG) {
//            this.keyAlias = localProperties.getProperty(Config.DEBUG_ALIAS)
//            this.keyPassword = localProperties.getProperty(Config.DEBUG_PASSWORD)
//            this.storeFile = File(rootDir, "config/Debug.jks")
//            this.storePassword = keyPassword
//        }
//        create(Config.RELEASE) {
//            this.keyAlias = localProperties.getProperty(Config.RELEASE_ALIAS)
//            this.keyPassword = localProperties.getProperty(Config.RELEASE_PASSWORD)
//            this.storeFile = File(rootDir, "config/Release.jks")
//            this.storePassword = keyPassword
//        }
    }

    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = Config.multiDexEnabled
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
//        release {
//            isMinifyEnabled = false
//        }
    }


    buildFeatures {
        buildConfig = true
        viewBinding = true

        // Disable unused AGP features
        aidl = false
        prefab = false
        shaders = false
        compose = false
        resValues = false
        renderScript = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.core.shell)
    implementation(projects.core.base)
    implementation(projects.core.res)
    implementation(libs.multidex)

    implementation(libs.coreKtx)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.androidx.ktx)

    // DI
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(projects.dependencyInjection.android)
    implementation(projects.dependencyInjection.core)

    debugImplementation(libs.leakcanary)
    debugImplementation(projects.core.debug)

    debugImplementation(libs.plutolib.debug)
    releaseImplementation(libs.plutolib.release)


    // ViewBinding Util
    implementation(libs.viewbindingpropertydelegate)

    implementation(libs.cicerone)
    implementation(libs.bundles.glide)
    implementation(libs.circleImageView)
    implementation("com.adkhambek.leo:leo:0.0.3")

    // Features
    implementation(projects.feature.auth.model)
    implementation(projects.feature.auth.impl)
    implementation(projects.feature.auth.api)
}
