package com.silva.nexuside.fragment

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.databinding.FragmentProjectsBinding;
import android.view.animation.AccelerateDecelerateInterpolator;

class ProjectsFragment: AppCompatActivity() {
    
    private lateinit var binding: FragmentProjectsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val enterAnimation = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }
        enterTransition = enterAnimation
        val exitAnimation = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }
        exitTransition = exitAnimation
    }
    
    override fun onCreateView(LayoutInflater inflater, ViewGroup container, savedInstanceState: Bundle?) {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProjectsBinding.inflate(inflater)
        
        return binding.root
    }
    
}