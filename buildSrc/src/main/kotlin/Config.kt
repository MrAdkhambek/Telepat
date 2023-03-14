import org.gradle.api.JavaVersion

object Config {
    const val compileSdkVersion = 32

    const val applicationId = "com.adkhambek.telepat"

    const val minSdkVersion = 21
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val multiDexEnabled = true
    const val consumerProguardFiles = "consumer-rules.pro"

    const val runnerBuilder = "runnerBuilder"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val testInstrumentationRunnerArguments = "de.mannodermaus.junit5.AndroidJUnit5Builder"

    val javaVersion = JavaVersion.VERSION_11

    const val RELEASE: String = "release"
    const val RELEASE_ALIAS: String = "ALIAS"
    const val RELEASE_PASSWORD: String = "PASSWORD"

    const val DEBUG: String = "debug"
    const val DEBUG_ALIAS: String = "ALIAS_DEBUG"
    const val DEBUG_PASSWORD: String = "PASSWORD_DEBUG"


    val freeCompilerArgs = listOf(
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        "-opt-in=kotlin.contracts.ExperimentalContracts",
        "-opt-in=kotlin.RequiresOptIn",
        "-Xjvm-default=all",
    )

    val excludes = listOf(
        "META-INF/ASL2.0",
        "META-INF/DEPENDENCIES",
        "META-INF/INDEX.LIST",
        "META-INF/LICENSE",
        "META-INF/LICENSE.txt",
        "META-INF/NOTICE",
        "META-INF/NOTICE.txt",
        "META-INF/*.kotlin_module",
        "META-INF/license.txt",
        "META-INF/notice.txt",
        "META-INF/io.netty.versions.properties",
    )
}
