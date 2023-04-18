pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
        flatDir {
            dirs("libs")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://www.jitpack.io")
        flatDir {
            dirs("libs")
        }
    }
}
rootProject.name = "socket-test"
include (
    ":app",
    ":common:common-base",
    ":common:common-model",
    ":common:common-resource",
    ":common:common-util",
    ":core:core-data",
    ":core:core-database",
    ":core:core-network",
)
