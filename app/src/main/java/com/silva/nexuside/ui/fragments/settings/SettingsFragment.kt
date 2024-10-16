package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle

import androidx.preference.Preference
import androidx.preference.PreferenceManager

import com.silva.nexuside.resources.R
import com.silva.nexuside.ui.base.BasePreferenceFragment

class SettingsFragment : BasePreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey)
    }
}