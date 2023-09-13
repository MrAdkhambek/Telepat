@file:Suppress(
    "UnstableApiUsage",
)

import com.adkhambek.app.Config
import com.adkhambek.app.configureKotlinAndroid
import com.adkhambek.app.configureTestAndroid
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidDataSourceActualConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("kotlin-parcelize")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureTestAndroid(this)
                defaultConfig.targetSdk = Config.targetSdkVersion
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                // Kotlin and kotlin core libraries
                add("implementation", libs.findBundle("kotlin.core").get())

                // DI
                add("implementation", libs.findLibrary("dagger").get())
                add("implementation", project(":dependency-injection:core"))
            }
        }
    }
}
