package com.silva.nexuside.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.silva.nexuside.resources.databinding.FragmentBottomsheetBinding;
import com.silva.nexuside.ui.base.BaseFragment;

public class EditorBottomSheetFragment extends BaseFragment {
    
    private FragmentBottomsheetBinding binding;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentBottomsheetBinding.inflate(inflater);
        return binding.getRoot();
    }
    
}
