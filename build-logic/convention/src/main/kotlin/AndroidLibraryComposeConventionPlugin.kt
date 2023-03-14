import com.adkhambek.app.configureAndroidCompose
import com.adkhambek.app.configureTestAndroid
import com.adkhambek.app.kotlinOptions
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        pluginManager.apply("com.android.library")
        val extension = extensions.getByType<LibraryExtension>()
        configureAndroidCompose(extension)
        configureTestAndroid(extension)

        extension.kotlinOptions {
            freeCompilerArgs = (freeCompilerArgs + listOf("-Xexplicit-api=warning")).distinct()
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            // Compose
            add("implementation", libs.findBundle("compose").get())
            add("debugImplementation", libs.findLibrary("compose.tooling").get())
            add("runtimeOnly", libs.findLibrary("compose.tooling.preview").get())
            add("androidTestImplementation", libs.findLibrary("compose.test").get())
            add("androidTestImplementation", libs.findLibrary("compose.ui.test").get())

            // Accompanist
            add("implementation", libs.findBundle("accompanist").get())

            // TODO : Remove this dependency once we upgrade to Android Studio Dolphin b/228889042
            // These dependencies are currently necessary to render Compose previews
            add("debugImplementation", libs.findLibrary("androidx.customview.poolingcontainer").get())
        }
    }
}
