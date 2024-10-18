package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle
import com.silva.nexuside.resources.Xmls
import com.silva.nexuside.ui.base.BasePreferenceFragment

class SettingsPluginsFragment : BasePreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(Xmls.prefs_plugins, rootKey)
    }
}
