/*
 * Copyright 2021 Apollo Agriculture
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apolloagriculture

import android.os.Build
import android.os.StrictMode

class ApolloAgricultureDebug : ApolloAgriculture() {

    override fun onCreate() {
        super.onCreate()

//        initStrictMode()
    }

    private fun initStrictMode() {
        val threadPolicy = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskWrites()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectResourceMismatches()
            }
            else -> {
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskWrites()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .penaltyDeath()
            }
        }
        StrictMode.setThreadPolicy(threadPolicy.build())

        val vmPolicy = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectLeakedSqlLiteObjects()
                    .detectFileUriExposure()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectCleartextNetwork()
                    .detectActivityLeaks()
            }
            else -> {
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectLeakedSqlLiteObjects()
                    .detectFileUriExposure()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectActivityLeaks()
            }
        }
        StrictMode.setVmPolicy(vmPolicy.build())
    }
}