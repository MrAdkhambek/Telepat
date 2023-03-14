plugins {
    id("com.adkhambek.kotlin")
}

kotlin {
    explicitApi()
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

dependencies {
    api(libs.javax.inject)
}
