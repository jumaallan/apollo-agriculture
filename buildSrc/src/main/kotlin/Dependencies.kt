object Versions {

    // Kotlin
    const val kotlinVersion = "1.4.21"
    const val coreKtx = "1.5.0-beta01"

    // DI - KOIN
    const val koin = "3.1.2"

    // Timber
    const val timber = "5.0.0"

    // Firebase
    const val firebase = "26.0.0"

    // test libraries
    const val espresso = "3.4.0-alpha02"
    const val annotation = "1.2.0-alpha01"

}

object Libraries {

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // DI - KOIN
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Firebase
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"

    // test libraries
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
}

object BuildModules {
    const val coreModule = ":internal:core"
    const val dataModule = ":internal:data"
    const val networkModule = ":internal:network"
}