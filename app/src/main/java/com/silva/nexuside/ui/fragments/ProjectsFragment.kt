package com.silva.nexuside.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.transition.MaterialSharedAxis
import com.silva.nexuside.databinding.FragmentProjectsBinding
import android.view.animation.AccelerateDecelerateInterpolator
import com.silva.nexuside.project.ProjectManager
import com.silva.nexuside.adapters.ProjectsAdapter
import java.util.ArrayList

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
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        init()
        return binding.root
    }
    
    fun init() {
        configureToolbar()
        configureProjects()
    }
    
    fun configureToolbar() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    
    fun configureProjects() {
        val projectManager = ProjectManager()
        val projectList = projectManager.listOfProjects()
        
        if(projectList.isEmpty()) {
            binding.noProjectsFound.visibility = View.VISIBLE
            binding.projects.visibility = View.GONE
        } else {
            binding.noProjectsFound.visibility = View.GONE
            binding.projects.visibility = View.VISIBLE
            binding.projects.layoutManager = LinearLayoutManager(requireContext())
            val adapter = ProjectsAdapter(projectList)
            binding.projects.adapter = adapter
        }
    }
    
}