
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.example.weather_utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// to publish the library u need to run these commands
//             ./gradlew :weather-utils:assembleRelease
//             ./gradlew :weather-utils:publish
// after that update the maven url in the gradle setting file
// finally comment out the library dependency in both tomel and gradle files

publishing {
    publications {
        create<MavenPublication>("weatherUtils") {
            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
            groupId = "com.passant.weatherutils"
            artifactId = "weather-utils"
            version = "1.0.0"
        }
    }

    repositories {
        maven {
            url = uri("${buildDir}/repo")
        }
    }
}
