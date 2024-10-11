package com.silva.nexuside.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.databinding.FragmentProjectsBinding;
import android.view.animation.AccelerateDecelerateInterpolator;

public class ProjectsFragment extends Fragment {
    
    FragmentProjectsBinding binding;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.X, false);
    	enterTransition.setDuration(700);
    	enterTransition.setInterpolator(new AccelerateDecelerateInterpolator());
        setEnterTransition(enterTransition);
        MaterialSharedAxis exitTransition = new MaterialSharedAxis(MaterialSharedAxis.X, true);
    	enterTransition.setDuration(700);
    	enterTransition.setInterpolator(new AccelerateDecelerateInterpolator());
        setExitTransition(exitTransition);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentProjectsBinding.inflate(inflater);
        binding.listView.setVisibility(View.GONE);
        return binding.getRoot();
    }
    
    
}
