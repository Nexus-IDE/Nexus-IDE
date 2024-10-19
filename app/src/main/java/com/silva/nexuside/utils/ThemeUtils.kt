package com.silva.nexuside.utils

import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {
    
    fun setTheme(uiMode: Int) {
        AppCompatDelegate.setDefaultNightMode(uiMode)
    }
    
    fun setLightTheme() {
        setTheme(AppCompatDelegate.MODE_NIGHT_NO)
    }
    
    fun setDarkTheme() {
        setTheme(AppCompatDelegate.MODE_NIGHT_YES)
    }
    
    fun setFollowSysTheme() {
         setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
}