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
        maven {
            val repoPath = if (System.getProperty("os.name").startsWith("Windows")) {
                "C:\\Users\\DELL\\AndroidStudioProjects\\WeatherHub\\weather-utils\\build\\repo"
            } else {
                "/home/runner/work/WeatherHub/WeatherHub/weather-utils/build/repo"
            }
            url = uri(repoPath)
        }
//        maven { url = uri("C:\\Users\\DELL\\AndroidStudioProjects\\WeatherHub\\weather-utils\\build\\repo") }
    }
}

rootProject.name = "WeatherHub"
include(":app")
include(":weather-utils")
