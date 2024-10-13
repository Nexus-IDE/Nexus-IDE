package com.silva.nexuside.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.silva.nexuside.databinding.FragmentWorkspaceBinding;

public class WorkspaceFragment extends Fragment {
    
    FragmentWorkspaceBinding binding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentWorkspaceBinding.inflate(inflater);
        return binding.getRoot();
    }
    
    
}
