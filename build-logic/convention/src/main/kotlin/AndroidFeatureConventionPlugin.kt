@file:Suppress(
    "UnstableApiUsage",
)

import com.adkhambek.app.Config
import com.adkhambek.app.configureKotlinAndroid
import com.adkhambek.app.configureTestAndroid
import com.android.build.gradle.LibraryExtension
import com.squareup.anvil.plugin.AnvilExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("kotlin-parcelize")
            apply("com.google.devtools.ksp")
            apply("com.squareup.anvil")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            configureTestAndroid(this)
            defaultConfig.targetSdk = Config.targetSdkVersion
        }

        extensions.configure<AnvilExtension> {
            this.generateDaggerFactories.set(true)
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            // Kotlin and kotlin core libraries
            add("implementation", libs.findBundle("kotlin").get())

            // AndroidX activity and fragment
            add("implementation", libs.findBundle("androidx.ktx").get())

            // Lifecycle extensions
            add("implementation", libs.findBundle("lifecycle").get())

            // Architecture Pattern MVI - MVI orbit
            add("implementation", libs.findBundle("mvi.orbit").get())
            add("implementation", libs.findLibrary("mvi-test").get())

            // Core modules
            add("implementation", project(":core:shell"))
            add("implementation", project(":core:base"))
            add("implementation", project(":core:res"))

            // Navigation
            add("implementation", project(":navigation:core"))
            add("implementation", libs.findBundle("navigation").get())

            // DI
            add("implementation", libs.findLibrary("dagger").get())
            add("implementation", project(":dependency-injection:core"))
            add("implementation", project(":dependency-injection:android"))
            add("anvil", project(":dependency-injection:generators"))
        }
    }
}
