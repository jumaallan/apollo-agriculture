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
    api(project(BuildModules.coreModule))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)

    // DI - KOIN
    implementation(Libraries.koin)

    // Network - Retrofit, OKHTTP
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

    // timber
    implementation(Libraries.timber)
}