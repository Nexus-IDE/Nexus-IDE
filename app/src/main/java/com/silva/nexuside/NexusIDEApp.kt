package com.silva.nexuside

import android.app.Application
import com.google.android.material.color.DynamicColors

class NexusIDEApp : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
