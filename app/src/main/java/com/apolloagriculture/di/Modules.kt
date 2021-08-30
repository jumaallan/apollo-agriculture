package com.apolloagriculture.di

import com.apolloagriculture.data.repository.WeatherRepository
import com.apolloagriculture.data.repository.WeatherRepositoryImpl
import com.apolloagriculture.views.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { WeatherViewModel(get()) }
}

val appModules: List<Module> = listOf(
    viewModelModule,
    repositoryModule
)