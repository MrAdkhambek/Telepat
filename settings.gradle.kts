@file:Suppress(
    "UnstableApiUsage"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://plugins.gradle.org/m2/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

buildCache {
    local {
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "Telepat"

include(":app")

include(
    ":core:shell",
    ":core:base",
    ":core:res",
    ":core:debug",
)

include(
    ":navigation:core"
)

include(
    ":dependency-injection:core",
    ":dependency-injection:android",
    ":dependency-injection:generators",
)

include(
    "feature:auth:api",
    "feature:auth:impl",
    "feature:auth:model",
)