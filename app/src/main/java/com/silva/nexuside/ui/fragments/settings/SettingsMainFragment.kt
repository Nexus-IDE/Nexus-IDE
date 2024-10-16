package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle

import com.silva.nexuside.resources.R
import com.silva.nexuside.ui.base.BasePreferenceFragment

class SettingsMainFragment : BasePreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey)
    }
}