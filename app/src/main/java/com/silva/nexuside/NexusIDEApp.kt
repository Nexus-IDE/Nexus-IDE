package com.silva.nexuside

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.silva.nexuside.di.preferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NexusIDEApp : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NexusIDEApp)
            modules(preferencesModule)
        }
    }
}
