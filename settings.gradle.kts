pluginManagement {
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
        // comment out and add ur absolute url, after publish the library locally
//        maven { url = uri("C:\\Users\\DELL\\AndroidStudioProjects\\WeatherHub\\weather-utils\\build\\repo") }
    }
}

rootProject.name = "WeatherHub"
include(":app")
include(":weather-utils")
