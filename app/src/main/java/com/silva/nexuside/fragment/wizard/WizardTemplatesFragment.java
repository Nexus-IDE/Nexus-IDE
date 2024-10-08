package com.silva.nexuside.fragment.wizard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.activity.MainActivity;
import com.silva.nexuside.adapters.TemplatesAdapter;
import com.silva.nexuside.databinding.FragmentWizardTemplatesBinding;
import com.silva.nexuside.enums.WizardTemplates;
import com.silva.nexuside.fragment.HomeFragment;
import com.silva.nexuside.R;
import java.util.List;

public class WizardTemplatesFragment extends Fragment {
    
    private FragmentWizardTemplatesBinding binding;
    
    private List<WizardTemplates> templates = WizardTemplates.getAvailableList();
    
    private MainActivity mainActivity;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));
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
