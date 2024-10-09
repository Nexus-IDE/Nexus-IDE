package com.silva.nexuside;

import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class NexusIDEApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Registrar o CrashHandler
        CrashHandler crashHandler = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}