package com.silva.nexuside.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.silva.nexuside.adapters.ProjectsAdapter
import com.silva.nexuside.resources.databinding.FragmentProjectsBinding
import com.silva.nexuside.project.ProjectManager
import com.silva.nexuside.ui.base.BaseFragment

class ProjectsFragment : BaseFragment() {

    private lateinit var binding: FragmentProjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    fun configureProjects() {
        val projectManager = ProjectManager()
        val projectList = projectManager.listOfProjects()

        if (projectList.isEmpty()) {
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
