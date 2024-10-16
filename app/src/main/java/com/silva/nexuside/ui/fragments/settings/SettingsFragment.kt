package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle

import androidx.fragment.app.Fragment

import com.silva.nexuside.resources.R
import com.silva.nexuside.databinding.FragmentSettingsBinding
import com.silva.nexuside.ui.base.BaseFragment

class SettingsFragment(
    private val startFragment: Fragment
) : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        openFragment(startFragment, startFragment.toString())
    }
    
    private fun openFragment(fragment: Fragment, lue: String) {
        parentFragmentManager.beginTransaction()
            .replace(binding.fragmentSettings.id, fragment, lue)
            .addToBackStack(null)
            .commit()
    }
}