package com.silva.nexuside;

import android.app.Application;

public class NexusIDEApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Registrar o CrashHandler
        CrashHandler crashHandler = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }
}