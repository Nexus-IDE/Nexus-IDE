package com.silva.nexuside.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import com.silva.nexuside.R;
import com.silva.nexuside.databinding.ActivityEditorBinding;

public class EditorActivity extends AppCompatActivity {
    
    ActivityEditorBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }
    
    public void init() {
        //toolbar and drawer
    	setSupportActionBar(binding.toolbar);
        binding.drawer.setScrimColor(Color.TRANSPARENT);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            EditorActivity.this,
            binding.drawer,
            binding.toolbar,
            R.string.app_name,
            R.string.app_name
            ) {
                @Override
                public void onDrawerSlide(View v, float slideOffset) {
                    super.onDrawerSlide(v, slideOffset);
                float sliderX = v.getWidth() * slideOffset;
                binding.coordinator.setTranslationX(sliderX);
                }
                
            };
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();
        
    }
    
    @Override
    @Deprecated
    public void onBackPressed() {
        super.onBackPressed();
        if(binding.drawer.isDrawerOpen(GravityCompat.START))
        	binding.drawer.closeDrawer(GravityCompat.START);
    }
    
    
    
}
