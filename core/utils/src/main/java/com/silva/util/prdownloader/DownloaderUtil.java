package com.silva.util.prdownloader;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.util.Log;
import android.widget.TextView;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Status;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderUtil {
    
    String TAG = "DOWNLOADER";
    
    int downloadID;
    
    public DownloaderUtil(Context context) {
    	PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
        .setDatabaseEnabled(true)
        .build();
        PRDownloader.initialize(context, config);
    }
    
    public void start(String url, String dir,  String fileName, LinearProgressIndicator progressIndicator, TextView textProgress, OnStatusChanged status) {
    	if(Status.RUNNING == PRDownloader.getStatus(downloadID) || Status.PAUSED == PRDownloader.getStatus(downloadID)) {
    		return;
    	}
        
        try {
        	new URL(url);
            downloadID = PRDownloader.download(url,dir, fileName).build()
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
            .setOnProgressListener(progress -> {
                    long currentBytes = progress.currentBytes;
                    long totalBytes = progress.totalBytes;
                    
                    if(totalBytes != -1) {
                    	long progressPercent = currentBytes * 100 / totalBytes;
                        int progresss = (int)progressPercent;
                        progressIndicator.setProgress(progresss);
                        textProgress.setText(progresss + "%");
                    } else {
                        progressIndicator.setIndeterminate(true);
                        textProgress.setText("0%");
                    }
            })
            .start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {
                    status.onCompleted();
                }
                @Override
                public void onError(Error error) {
                    status.onError(error);
                }
            });
        } catch(MalformedURLException err) {
        	Log.e(TAG, err.getMessage());
        } catch(Exception | OutOfMemoryError err) {
            Log.e(TAG, err.getMessage());
        }
    }
    
    public String sizeFormat(int size) {
    	String string = "0 B";
        if(size < 1000) {
        	string = (long)size + " B";
        } else if(size < 1000000) {
        	string = new DecimalFormat("0.00").format(size / 1000) + " KB";
        } else if(size < 1.0E9) {
        	string = new DecimalFormat("0.00").format(size / 1000000) + " MB";
        } else {
            string = new DecimalFormat("0.00").format(size / 1.0E9) + " GB";
        }
        return string;
    }
    
    public interface OnStatusChanged {
		public void onCompleted();
		public void onError(Error error);
	}
    
}