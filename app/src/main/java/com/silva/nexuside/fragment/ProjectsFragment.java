package com.silva.nexuside.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.silva.nexuside.databinding.FragmentWorkspaceBinding;

public class ProjectsFragment extends Fragment {
    
    FragmentProjectsBinding binding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentProjectsBinding.inflate(inflater);
        binding.listView.setVisibility(View.GONE);
        return binding.getRoot();
    }
    
    
}
