package com.silva.nexuside.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

import com.silva.nexuside.core.settings.repositories.PreferencesRepository
import com.silva.nexuside.core.settings.viewmodels.PreferencesViewModel

const val APP_PREFERENCES = "app_preferences"

val preferencesModule = module {
    singleOf(::PreferencesRepository)
    viewModelOf(::PreferencesViewModel)

    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile(APP_PREFERENCES)
        }
    }
}
