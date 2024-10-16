package com.silva.nexuside.ui.fragments;

import android.os.Bundle;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.fragment.app.Fragment;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.ui.activities.MainActivity;
import com.silva.nexuside.ui.base.BaseFragment;
import com.silva.nexuside.databinding.FragmentHomeBinding;
import com.silva.nexuside.R;

public class HomeFragment extends BaseFragment {
    
    private FragmentHomeBinding binding;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	binding = FragmentHomeBinding.inflate(inflater);
        binding.createProject.setOnClickListener(view -> {
            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment, new WizardTemplatesFragment(), "WizardTemplates")
                .addToBackStack(null)
                .commit();
        });
        binding.openProject.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                 .replace(R.id.fragment, new ProjectsFragment(), "Projects")
                 .addToBackStack(null)
                 .commit();
        });
        return binding.getRoot();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        this.binding = null;
    }
    
}