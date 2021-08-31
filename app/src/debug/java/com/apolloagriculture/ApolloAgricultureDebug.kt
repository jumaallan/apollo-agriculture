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