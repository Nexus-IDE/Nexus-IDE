package com.silva.nexuside.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.silva.nexuside.R;
import com.silva.nexuside.databinding.ActivityMainBinding;
import com.silva.nexuside.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity{
    
    private ActivityMainBinding binding;
    private String mLast;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(checkPerms(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        init();
    }
    
    public void init() {
    	openFragment(new HomeFragment(), "Home");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        this.binding = null;
    }
    
    public void openFragment(Fragment fragment, String tag) {
    	getSupportFragmentManager().beginTransaction()
        .replace(binding.fragment.getId(), fragment, tag)
        .addToBackStack(null)
        .commit();
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int request, String[] perms, int[] results) {
        super.onRequestPermissionsResult(request, perms, results);
        if(request == 1000) {
        	init();
        }
    }
    
    
    public boolean checkPerms(String... permissions) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        	if(!Environment.isExternalStorageManager()) {
        		try {
        			Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", this.getPackageName())));
                    startActivity(intent);
        		} catch(Exception err) {
        			Intent intent = new Intent();
				    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
					startActivity(intent);
        		}
        	}
        } else {
            for(String s : permissions) {
            	if(ContextCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_DENIED) {
            		new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.permission)
                    .setMessage(R.string.permission_warning)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        ActivityCompat.requestPermissions(MainActivity.this, permissions, 1000);
                    })
                    .create()
                    .show();
                    return false;
            	}
            }
        }
    	return true;
    }
    
}
