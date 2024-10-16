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
import com.silva.nexuside.ui.fragments.settings.SettingsFragment;
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
    	
        binding.createProject.setOnClickListener(view -> openFragment(new WizardTemplatesFragment(), "WizardTemplates"));
        binding.openProject.setOnClickListener(view -> openFragment(new ProjectsFragment(), "Projects"));
        binding.settings.setOnClickListener(view -> openFragment(new SettingsFragment(), "SettingsMain"));
        
        return binding.getRoot();
    }
    
    private void openFragment(Fragment fragment, String lue) {
        getParentFragmentManager().beginTransaction()
                 .replace(R.id.fragment, fragment, lue)
                 .addToBackStack(null)
                 .commit();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        this.binding = null;
    }
    
}