package com.silva.nexuside.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.silva.nexuside.databinding.FragmentSettingsBinding
import com.silva.nexuside.ui.base.BaseFragment

class SettingsFragment(
    private val startFragment: Fragment
) : BaseFragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        openFragment(startFragment, startFragment.toString())
        return binding.root
    }

    private fun openFragment(
        fragment: Fragment,
        lue: String = fragment.toString()
    ) {
        parentFragmentManager.beginTransaction()
            .replace(binding.fragmentSettings.id, fragment, lue)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}