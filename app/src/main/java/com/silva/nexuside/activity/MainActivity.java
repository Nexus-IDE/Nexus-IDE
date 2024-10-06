package com.silva.nexuside.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.downloader.Error;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.silva.nexuside.R;
import com.silva.nexuside.databinding.ActivityMainBinding;
import com.silva.nexuside.databinding.LayoutInstallResourcesBinding;
import com.silva.nexuside.fragment.HomeFragment;
import com.silva.util.prdownloader.DownloaderUtil;
import java.io.File;

public class MainActivity extends AppCompatActivity{
    
    private ActivityMainBinding binding;
    private String mLast;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPerms(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        init();
    }
    
    public void init() {
    	openFragment(new HomeFragment(), "Home");
        checkResourcesInstalled();
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
    
    public boolean checkResourcesInstalled() {
        if(new File(getApplicationContext().getFilesDir() + "/completion/editor/index.json").exists()) {
        	return true;
        } else {
            DownloaderUtil downloader = new DownloaderUtil(this);
            LayoutInstallResourcesBinding bindingDialog = LayoutInstallResourcesBinding.inflate(getLayoutInflater());
            new MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_download)
            .setTitle(R.string.install_required_rscs)
            .setMessage(R.string.required_rscs_description)
            .setView(bindingDialog.getRoot())
            .create()
            .show();
            bindingDialog.installButton.setOnClickListener((v) -> {
                bindingDialog.installButton.setEnabled(false);
                    bindingDialog.installProgressText.setVisibility(View.VISIBLE);
                    bindingDialog.installProgress.setVisibility(View.VISIBLE);
                    bindingDialog.installButton.setVisibility(View.GONE);
                    downloader.start("https://firebasestorage.googleapis.com/v0/b/wavechat-53b2a.appspot.com/o/index.json?alt=media&token=afd80b57-6263-46a4-a65c-9b1829f2e08b", getApplicationContext().getFilesDir() + "/completion/editor/", "index.json", bindingDialog.installProgress, bindingDialog.installProgressText, new DownloaderUtil.OnStatusChanged() {
                        @Override
                        public void onCompleted() {
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onError(Error error) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                        
                    });
            });
        }
        return false;
    }
    
    
    public boolean checkPerms(String... permissions) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        	if(!Environment.isExternalStorageManager()) {
        	   new MaterialAlertDialogBuilder(this)
        	     .setTitle(R.string.permission)
        	     .setMessage(R.string.permission_message)
        	     .setCancelable(false)
        	     .setPositiveButton(android.R.string.ok, (dialog, which) -> {
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
        	     })
        	  	 .create()
        	  	 .show();
        	  	 return false;
        	}
        } else {
            for(String s : permissions) {
            	if(ContextCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_DENIED) {
            		new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.permission)
                    .setMessage(R.string.permission_message)
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
