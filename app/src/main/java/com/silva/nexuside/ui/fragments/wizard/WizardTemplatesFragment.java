package com.silva.nexuside.ui.fragments.wizard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.ui.activities.MainActivity;
import com.silva.nexuside.adapters.TemplatesAdapter;
import com.silva.nexuside.databinding.FragmentWizardTemplatesBinding;
import com.silva.nexuside.enums.WizardTemplates;
import com.silva.nexuside.ui.fragments.HomeFragment;
import com.silva.nexuside.resources.res;
import java.util.List;
import android.view.animation.AccelerateDecelerateInterpolator;

public class WizardTemplatesFragment extends Fragment {
    
    private FragmentWizardTemplatesBinding binding;
    
    private List<WizardTemplates> templates = WizardTemplates.getAvailableList();
    
    private MainActivity mainActivity;
    
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
    	binding = FragmentWizardTemplatesBinding.inflate(inflater);
        mainActivity = (MainActivity) getActivity();
        binding.templates.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.templates.setAdapter(new TemplatesAdapter(getContext(), templates, mainActivity));
        binding.exit.setOnClickListener(view -> {
                mainActivity.onBackPressed();
        });
        return binding.getRoot();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        this.binding = null;
    }
    
}
