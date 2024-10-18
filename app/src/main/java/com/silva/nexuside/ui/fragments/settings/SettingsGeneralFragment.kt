package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.silva.nexuside.adapters.OptionsAdapter
import com.silva.nexuside.databinding.LayoutDialogSelectListviewBinding
import com.silva.nexuside.resources.Strings
import com.silva.nexuside.resources.Xmls
import com.silva.nexuside.ui.base.BasePreferenceFragment

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
            val binding = LayoutDialogSelectListviewBinding.inflate(inflater)
            val items =
                listOf(
                    getContext()?.getString(Strings.ui_mode_value_followsys),
                    getContext()?.getString(Strings.ui_mode_value_light),
                    getContext()?.getString(Strings.ui_mode_value_dark),
                )
            val adapter = itens?.let { OptionsAdapter(it) }
            binding.listview.adapter = adapter

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(Strings.ui_mode_title))
                .setPositiveButton(getString(Strings.save)) { dialog, which -> }
                .setNegativeButton(getString(Strings.cancel), null)
                .setView(binding.root)
                .show()
            true
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
