package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle

import androidx.preference.Preference
import androidx.preference.PreferenceManager

import com.silva.nexuside.resources.R
import com.silva.nexuside.databinding.FragmentSettingsBinding
import com.silva.nexuside.ui.base.BaseFragment
import com.silva.nexuside.ui.base.BasePreferenceFragment

class SettingsFragment(
    private val startFragment: BaseFragment
) : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        openFragment(startFragment)
    }
    
    private fun openFragment(fragment: BaseFragment, lue: String) {
        parentFragmentManager.beginTransaction()
            .replace(binding.fragmentSettings.id, fragment, lue)
            .addToBackStack(null)
            .commit()
    }
}

class SettingsMainFragment : BasePreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey)
    }
}