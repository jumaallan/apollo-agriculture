Apollo Agriculture Android Take Home Assignment
==============

Writing Apollo Agriculture App using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), in 100% Kotlin, using Android Jetpack Components, and in Compose :rocket:

Requirements
----
- Add a second screen to the app with weather data for the current location. Use the following initial wireframe we've made:
  ![](docs/prototype.png)
  
- The output from our weather API looks like this:
 ```json
{
  "today": {
  "lowTemp": 23.36,
  "highTemp": 24.66,
  "icon": "CLEAR_DAY",
  "description": "clear sky"
  },
  "tomorrow": {
  "lowTemp": 23.63,
  "highTemp": 24.9,
  "icon": "SCATTERED_CLOUDS_DAY",
  "description": "scattered clouds"
  },
  "dayAfterTomorrow": {
  "lowTemp": 22.73,
  "highTemp": 25.14,
  "icon": "BROKEN_OVERCAST_CLOUDS_DAY",
  "description": "broken clouds"
  }
}
 ```
Feel free to use however you think best in the app, it's also available at `https://apollo-web-public.s3.eu-west-1.amazonaws.com/eng/android-takehomeassignment/weather.json` so feel free to add an API client.
- Documentation for the API is available in `API.md` in this folder.
- Use any libraries for Android that you want, but we will need to be able to build your code to evaluate it.

### A few hiccups

I noticed the previews are not rendering since I am injecting the viewmodel in the composables. I need to research and find a way to fix the previews. Found the [issue here](https://github.com/InsertKoinIO/koin/issues/1072)

I am not able to test the Room setup too, so I will ignore the implementation on the app. The reason is, I am using JUnit5 and Roboelectric doesn't support it.

### Prerequisites - Unit Tests

#### Spek

This allows us to easily define specifications in a clear, understandable, human-readable way. This framework allows you to describe tests and expected behaviors in a more readable way.

To run tests in Android Studio you need to install Spek Framework plugin (search for Spek Framework).

The UI test run normally, either on a device or an emulator, without any special plugin or dependency.


### How it's built

* Technologies used
    * [Kotlin](https://kotlinlang.org/)
    * [Compose](https://developer.android.com/jetpack/compose) Because **COMPOSE IS FINALLY STABLE**
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
    * [KOIN](https://insert-koin.io/)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Chucker](https://github.com/ChuckerTeam/chucker)
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Timber](https://github.com/JakeWharton/timber)
    * [Leak Canary](https://github.com/square/leakcanary)

* Architecture
    * MVVM - Model View View Model

* Tests
    * [JUnit5](https://junit.org/junit5/)
    * [Spek](https://www.spekframework.org/)
    * [MockK](https://github.com/mockk/mockk)
    * [Turbine](https://github.com/cashapp/turbine)

* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Plugins
        * [Spotless](https://github.com/diffplug/spotless)
        * [Dokka](https://github.com/Kotlin/dokka)
        * [jacoco](https://github.com/jacoco/jacoco)
        * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle)
        * [Detekt](https://github.com/detekt/detekt)

* CI/CD
    * Github Actions
    * [Fastlane](https://fastlane.tools)


### Screenshots

I added some screenshots in the `screenshots` folder, in the root directory of the project. Added some GIFs to also show end to end test on the app

Light | Dark | GIF
--- | --- | ---
<img src="https://github.com/jumaallan/apollo-agriculture/blob/master/screenshots/weather_light.png" width="280"/> | <img src="https://github.com/jumaallan/apollo-agriculture/blob/master/screenshots/weather_dark.png" width="280"/> | <img src="https://github.com/jumaallan/apollo-agriculture/blob/master/screenshots/weather.gif" width="280"/>
