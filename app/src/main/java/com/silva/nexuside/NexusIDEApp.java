package com.silva.nexuside;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.material.color.DynamicColors;

public class NexusIDEApp extends Application {
    
    public static Context appContext;
    
    @Override
    public void onCreate() {
        super.onCreate();
        if(DynamicColors.isDynamicColorAvailable())
        	DynamicColors.applyToActivitiesIfAvailable(this);
    }
    
}