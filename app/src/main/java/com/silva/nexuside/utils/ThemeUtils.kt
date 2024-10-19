package com.silva.nexuside.utils

import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {
    
    fun applyTheme(uiMode: Int) {
        AppCompatDelegate.setDefaultNightMode(uiMode)
    }
    
    fun setLightTheme() {
        applyTheme(AppCompatDelegate.MODE_NIGHT_NO)
    }
    
    fun setDarkTheme() {
        applyTheme(AppCompatDelegate.MODE_NIGHT_YES)
    }
    
    fun setFollowSysTheme() {
         applyTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
}