@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.lib) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.anvil) apply false
}

allprojects {
    tasks.withType(KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = Config.javaVersion.toString()
            freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
        }
    }
}

subprojects {

    plugins.matching { it is AppPlugin || it is LibraryPlugin }.whenPluginAdded {
        configure<BaseExtension> {

            when (this) {
                is AppExtension -> {
                    packagingOptions {
                        resources.excludes.add("META-INF/*.kotlin_module")
                        resources.excludes.add("META-INF/*")
                    }
                }
                is LibraryExtension -> {
                    packagingOptions {
                        resources.excludes.add("META-INF/*.kotlin_module")
                        resources.excludes.add("META-INF/*")
                    }
                }
            }

            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = Config.javaVersion
                targetCompatibility = Config.javaVersion
            }

            defaultConfig {
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
                versionCode = Config.versionCode
                versionName = Config.versionName
                testInstrumentationRunner = Config.testInstrumentationRunner
            }

            packagingOptions.resources.excludes.addAll(Config.excludes)
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
