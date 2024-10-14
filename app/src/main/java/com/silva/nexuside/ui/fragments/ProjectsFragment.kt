package com.silva.nexuside.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialSharedAxis
import com.silva.nexuside.databinding.FragmentProjectsBinding
import android.view.animation.AccelerateDecelerateInterpolator

class ProjectsFragment: Fragment() {
    
    private lateinit var binding: FragmentProjectsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val sharedEnter = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }
        enterTransition = sharedEnter
        
        val sharedExit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }
        exitTransition = sharedExit
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProjectsBinding.inflate(inflater)
        
        return binding.root
    }
    
}