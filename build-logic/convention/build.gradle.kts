plugins {
    `kotlin-dsl`
}

group = "com.missed.app.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.parcelize.gradlePlugin)
    implementation(libs.kotlin.serialization.gradlePlugin)
    implementation(libs.google.ksp.gradlePlugin)
    implementation(libs.anvil.gradlePlugin)
}

gradlePlugin {
    plugins {

        register("androidApplicationCompose") {
            id = "com.adkhambek.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "com.adkhambek.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "com.adkhambek.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.adkhambek.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "com.adkhambek.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("DataSourceActualFeature") {
            id = "com.adkhambek.android.datasource.actual"
            implementationClass = "AndroidDataSourceActualConventionPlugin"
        }

        register("DataSourceExpectFeature") {
            id = "com.adkhambek.android.datasource.expect"
            implementationClass = "AndroidDataSourceExpectConventionPlugin"
        }

        register("kotlinLibrary") {
            id = "com.adkhambek.kotlin"
            implementationClass = "LibraryKotlinConventionPlugin"
        }
    }
}