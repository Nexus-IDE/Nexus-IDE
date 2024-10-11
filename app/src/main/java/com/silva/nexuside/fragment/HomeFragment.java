package com.silva.nexuside.fragment;

import android.os.Bundle;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.fragment.app.Fragment;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.activity.MainActivity;
import com.silva.nexuside.databinding.FragmentHomeBinding;
import com.silva.nexuside.R;
import com.silva.nexuside.fragment.wizard.*;

public class HomeFragment extends Fragment {
    
    private FragmentHomeBinding binding;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.X, true);
    	enterTransition.setDuration(700);
    	enterTransition.setInterpolator(new AccelerateDecelerateInterpolator());
        setEnterTransition(enterTransition);
        MaterialSharedAxis exitTransition = new MaterialSharedAxis(MaterialSharedAxis.X, false);
    	enterTransition.setDuration(700);
    	enterTransition.setInterpolator(new AccelerateDecelerateInterpolator());
        setExitTransition(exitTransition);
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