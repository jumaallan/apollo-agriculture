package com.apolloagriculture.data.di

import androidx.room.Room
import com.apolloagriculture.data.database.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private val databaseModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "apollo-agriculture-db"
        ).build()
    }
}

private val daoModule: Module = module {
    single { get<Database>().weatherDao() }
}

val dataModules: List<Module> = listOf(
    databaseModule,
    daoModule
)