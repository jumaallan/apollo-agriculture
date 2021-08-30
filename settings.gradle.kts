pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }

    plugins {
        id("com.android.application") version "4.1.0"
        id("org.jetbrains.kotlin.android") version "1.5.21"
        id("com.android.library") version "4.1.0"
        id("com.google.firebase.crashlytics") version "2.1.0"
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application", "com.android.library" -> useModule("com.android.tools.build:gradle:7.0.0")
                "com.google.firebase.crashlytics" -> useModule("com.google.firebase:firebase-crashlytics-gradle:2.1.0")
            }
        }
    }
}

include("app")

include(":internal:core")
include(":internal:data")
include(":internal:network")

rootProject.name = "ApolloAgriculture"