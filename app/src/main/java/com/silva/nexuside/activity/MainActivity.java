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
import android.widget.Toast;
import com.downloader.*;
import com.downloader.Error;
import java.net.MalformedURLException;
import java.net.URL;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

public class MainActivity extends AppCompatActivity{
    
    private ActivityMainBinding binding;
    private String mLast;
    private AlertDialog dialog = null;
    
    private int downloadID;
    
    private String TAG = "Downloader";
    
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
        init();
    }
    
    public boolean checkResourcesInstalled() {
        if(new File(getApplicationContext().getFilesDir() + "/completion/editor/index.json").exists()) {
        	return true;
        } else {
            PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build();
            PRDownloader.initialize(getWindow().getDecorView().getContext(), config);
            LayoutInstallResourcesBinding bindingDialog = LayoutInstallResourcesBinding.inflate(getLayoutInflater());
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setIcon(R.drawable.ic_download);
            builder.setTitle(R.string.install_required_rscs);
            builder.setMessage(R.string.required_rscs_description);
            builder.setView(bindingDialog.getRoot());
            dialog = builder.create();
            bindingDialog.installButton.setOnClickListener((v) -> {
                bindingDialog.installButton.setEnabled(false);
                    bindingDialog.installProgressText.setVisibility(View.VISIBLE);
                    bindingDialog.installProgress.setVisibility(View.VISIBLE);
                    downloadResources(bindingDialog);
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
    
    public void downloadResources(LayoutInstallResourcesBinding binding) {
        File indexPath = new File(getApplicationContext().getFilesDir(), "completion/java/");
    
        if (Status.RUNNING == PRDownloader.getStatus(downloadID) || Status.PAUSED == PRDownloader.getStatus(downloadID)) {
            return;
        }

        try {
           new URL("https://firebasestorage.googleapis.com/v0/b/wavechat-53b2a.appspot.com/o/index.json?alt=media&token=afd80b57-6263-46a4-a65c-9b1829f2e08b");

           downloadID = PRDownloader.download(
                "https://firebasestorage.googleapis.com/v0/b/wavechat-53b2a.appspot.com/o/index.json?alt=media&token=afd80b57-6263-46a4-a65c-9b1829f2e08b",
                indexPath.getAbsolutePath(),
                "index.json")
            .build()
            .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                @Override
                public void onStartOrResume() {
                    // TODO: Implement this method
                }
            })
            .setOnPauseListener(new OnPauseListener() {
                @Override
                public void onPause() {
                    // TODO: Implement this method
                }
            })
            .setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel() {
                    // TODO: Implement this method
                }
            })
            .setOnProgressListener(new OnProgressListener() {
                @Override
                public void onProgress(Progress progress) {
                    long currentBytes = progress.currentBytes;
                    long totalBytes = progress.totalBytes;

                    if (totalBytes != -1) {
                        long progressPercent = currentBytes * 100 / totalBytes;
                        int progresss = (int) progressPercent;
                        binding.installProgress.setIndeterminate(false);
                        binding.installProgress.setProgress(progresss);
                        binding.installProgressText.setText(progresss + "%");
                    } else {
                        binding.installProgress.setIndeterminate(true);
                        binding.installProgressText.setText("0%");
                    }
                }
            })
            .start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {
                    dialog.dismiss();
                }

                @Override
                public void onError(Error error) {
                    dialog.dismiss();
                }
            });
          } catch (MalformedURLException err) {
               Log.e(TAG, err.getMessage());
          } catch (Exception | OutOfMemoryError err) {
               Log.e(TAG, err.getMessage());
          }
       }
    
}
