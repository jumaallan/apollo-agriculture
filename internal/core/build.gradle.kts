plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.hiya.jacoco-android")
}

jacoco {
    toolVersion = "0.8.4"
}

android {

    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
        vectorDrawables.useSupportLibrary = true
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)

    // timber
    implementation(Libraries.timber)

    // Firebase crashlytics
    implementation(platform(Libraries.bom))
    implementation(Libraries.crashlytics)
}