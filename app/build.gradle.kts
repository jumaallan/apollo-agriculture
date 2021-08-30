plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.hiya.jacoco-android")
    id("de.mannodermaus.android-junit5")
    id("com.osacky.fladle")
    id("kotlin-android")
    id("kotlin-parcelize")
}

jacoco {
    toolVersion = "0.8.4"
}

android {

    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.apolloagriculture"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        animationsDisabled = true
        junitPlatform.apply {
            filters {
                includeEngines("spek2")
            }
            jacocoOptions {
                html.enabled = true
                xml.enabled = false
                csv.enabled = false
            }
        }
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
            all {
                testCoverage {
                }
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // Fixes - No tests found for given includes Error, when running Parameterized Unit test in Android Studio
    // https://stackoverflow.com/a/58125215
    tasks.withType<Test> {
        useJUnitPlatform()
        // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
        testLogging {
            lifecycle {
                events = mutableSetOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                showExceptions = true
                showCauses = true
                showStackTraces = true
                showStandardStreams = true
            }
            info.events = lifecycle.events
            info.exceptionFormat = lifecycle.exceptionFormat
        }

        val failedTests = mutableListOf<TestDescriptor>()
        val skippedTests = mutableListOf<TestDescriptor>()

        // See https://github.com/gradle/kotlin-dsl/issues/836
        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor) {}
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                when (result.resultType) {
                    TestResult.ResultType.FAILURE -> failedTests.add(testDescriptor)
                    TestResult.ResultType.SKIPPED -> skippedTests.add(testDescriptor)
                    else -> Unit
                }
            }

            override fun afterSuite(suite: TestDescriptor, result: TestResult) {
                if (suite.parent == null) { // root suite
                    logger.lifecycle("----")
                    logger.lifecycle("Test result: ${result.resultType}")
                    logger.lifecycle(
                        "Test summary: ${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped"
                    )
                    failedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tFailed Tests")
                    skippedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tSkipped Tests:")
                }
            }

            private infix fun List<TestDescriptor>.prefixedSummary(subject: String) {
                logger.lifecycle(subject)
                forEach { test -> logger.lifecycle("\t\t${test.displayName()}") }
            }

            private fun TestDescriptor.displayName() =
                parent?.let { "${it.name} - $name" } ?: name
        })
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../keystore/debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            versionNameSuffix = " - debug"
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // The Fladle RoboTest task is invoked with ./gradlew runFlank
    fladle {
        val roboTestBuildType = "debug"
        variant.set(roboTestBuildType)
        // Project Id is not needed if serviceAccountCredentials are set.
        // I didn't setup the serviceAccountCredentials, since this is an interview solution
        projectId.set("apolloagriculture")
        useOrchestrator.set(true)
        environmentVariables.set(project.provider { mapOf("clearPackageData" to "true") })
        // debugApk.set(project.provider { "$buildDir/outputs/bundle/debug/*.aab" }) // if its a bundle
        debugApk.set(project.provider { "$buildDir/outputs/apk/debug/*.apk" })
        roboScript.set("${project.layout.projectDirectory}/tools/ci/robotest/sanity-$roboTestBuildType.json")
        disableSharding.set(true)
        // This is the test timeout, there is also about 5 minutes of setup and teardown outside of this
        // Therefore a test timeout of 60seconds will mean the runFlank task takes ~6minutes
        testTimeout.set("60s")
        devices.set(
            listOf(
                mapOf("model" to "Pixel2", "version" to "28")
            )
        )
    }

    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        api(project(BuildModules.coreModule))
        api(project(BuildModules.dataModule))
        api(project(BuildModules.networkModule))

        implementation(Libraries.kotlinStdLib)
        implementation(Libraries.coreKtx)

        implementation("androidx.appcompat:appcompat:1.3.1")
        implementation("com.google.android.material:material:1.4.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.0")

        // DI - KOIN
        // Koin main features for Android (Scope,ViewModel)
        implementation(Libraries.koin)

        // leak canary
        debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

        // timber
        implementation(Libraries.timber)

        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    }
}