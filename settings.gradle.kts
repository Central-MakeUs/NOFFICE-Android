pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Noffice"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":data:notice")
include(":data:my")
include(":data:team")
include(":domain:team")
include(":domain:my")
include(":domain:home")
include(":feature:team")
include(":feature:home")
include(":feature:my")
include(":core:common")
include(":core:design-system")
include(":core:network")
