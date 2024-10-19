package com.silva.nexuside

import android.app.Application
import android.content.Intent
import com.google.android.material.color.DynamicColors
import com.silva.nexuside.di.preferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NexusIDEApp : Application() {

    private var uncaughtException: Thread.UncaughtExceptionHandler? = null

    override fun onCreate() {
        uncaughtException = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this::uncaughtException)
        
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
    
    private fun uncaughtException(thread: Thread, th: Throwable) {
        try {
          startActivity(
            Intent(this, CrashActivity::class.java).apply {
            putExtra("key_extra_error", ThrowableUtils.getFullStackTrace(th))
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
          )

          uncaughtException?.uncaughtException(thread, th)
          exitProcess(1)
        } catch (e: Throwable) {
          e.printStackTrace()
        }
    }

    fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NexusIDEApp)
            modules(preferencesModule)
        }
    }
}
