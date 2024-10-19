package com.silva.nexuside

import android.app.Application
import android.content.Intent
import com.google.android.material.color.DynamicColors
import com.silva.nexuside.di.preferencesModule
import com.silva.nexuside.ui.activities.CrashActivity
import com.blankj.utilcode.util.ThrowableUtils
import kotlin.system.exitProcess
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NexusIDEApp : Application() {

    private var uncaughtException: Thread.UncaughtExceptionHandler? = null

    override fun onCreate() {
        super.onCreate()
        uncaughtException = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this::uncaughtException)
        
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
    
    private fun uncaughtException(thread: Thread, th: Throwable) {
        Thread {
             try {
                 val intent = Intent(this, CrashActivity::class.java).apply {
                     putExtra("key_extra_error", ThrowableUtils.getFullStackTrace(th))
                     flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                           Intent.FLAG_ACTIVITY_CLEAR_TASK or
                           Intent.FLAG_ACTIVITY_CLEAR_TOP
                 }
                 startActivity(intent)
                 exitProcess(1)

         } catch (e: Throwable) {
             e.printStackTrace()
         }
        }.start()
    }

    fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NexusIDEApp)
            modules(preferencesModule)
        }
    }
}
