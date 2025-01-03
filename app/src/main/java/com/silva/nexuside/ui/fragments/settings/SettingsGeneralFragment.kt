package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.silva.nexuside.resources.Strings
import com.silva.nexuside.resources.Xmls
import com.silva.nexuside.ui.base.BasePreferenceFragment
import com.silva.nexuside.utils.ThemeUtils

class SettingsGeneralFragment : BasePreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(Xmls.prefs_general, rootKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val uiMode: Preference? = findPreference("pref_ui_mode")
        uiMode?.setOnPreferenceClickListener {
        
            var selected = 0
        
            val items = listOf(
                getContext()?.getString(Strings.ui_mode_value_followsys) ?: "Default FollowSys",
                getContext()?.getString(Strings.ui_mode_value_light) ?: "Default Light",
                getContext()?.getString(Strings.ui_mode_value_dark) ?: "Default Dark"
            )

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(Strings.ui_mode_title))
                .setPositiveButton(getString(Strings.save)) { dialog, which -> 
                    when(selected) {
                        0 -> ThemeUtils.setFollowSysTheme()
                        1 -> ThemeUtils.setLightTheme()
                        2 -> ThemeUtils.setDarkTheme()
                    }
                }
                .setNegativeButton(getString(Strings.cancel), null)
                .setSingleChoiceItems(items.toTypedArray(), selected) { dialog, which -> 
                    selected = which
                }
                .create()
                .show()
            true
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
