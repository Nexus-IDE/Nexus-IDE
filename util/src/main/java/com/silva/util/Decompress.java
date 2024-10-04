package com.silva.util;

import android.content.res.AssetManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileNotFoundException;
import android.content.Context;
import android.util.Log;

public class Decompress {
    private static final int BUFFER_SIZE = 1024 * 10;
    private static final String TAG = "Decompress";

    public static void unzipFromAssets(Context context, String zipFile, String destination) {
        try {
            if (destination == null || destination.length() == 0)
                destination = context.getFilesDir().getAbsolutePath();
            InputStream stream = context.getAssets().open(zipFile);
            unzip(stream, destination);
        } catch (IOException e) {
            Log.e(TAG, "unzipFromAssets: IOException", e);
        }
    }
    
    public static void unzipFromAssests(Context context, String zipFile, String subpath, String destination) {
    	AssetManager assetManager = context.getAssets();
        try {
        	InputStream inputStream = assetManager.open(zipFile);
            ZipInputStream zipInput = new ZipInputStream(inputStream);
            
            ZipEntry zipEntry;
            while((zipEntry = zipInput.getNextEntry()) != null) {
            	String entryName = zipEntry.getName();
                
                if(entryName.startsWith(subpath) && zipEntry.isDirectory()) {
                    String destinationPath = destination + entryName.substring(subpath.length());
                	File destinationDir = new File(destinationPath);
                    destinationDir.mkdirs();
                } else if(entryName.startsWith(subpath)) {
                    try {
                    	OutputStream outStream = new FileOutputStream(destination + entryName.substring(subpath.length()));
                        
                        byte[] buffer = new byte[1024];
                        int length;
                        while((length = zipInput.read(buffer)) > 0) {
                        	outStream.write(buffer, 0, length);
                        }
                        outStream.close();
                    } catch(Exception err) {
                    	err.printStackTrace();
                    }
                }
                
                zipInput.closeEntry();
            }
            zipInput.close();
            inputStream.close();
        } catch(Exception err) {
        	
        }
    }

    public static void unzip(String zipFile, String location) {
        try {
            FileInputStream fin = new FileInputStream(zipFile);
            unzip(fin, location);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "unzip: FileNotFoundException", e);
        }
    }

    public static void unzip(InputStream stream, String destination) {
        dirChecker(destination, "");
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            ZipInputStream zin = new ZipInputStream(stream);
            ZipEntry ze;

            while ((ze = zin.getNextEntry()) != null) {
                Log.v(TAG, "Unzipping " + ze.getName());

                if (ze.isDirectory()) {
                    dirChecker(destination, ze.getName());
                } else {
                    File f = new File(destination, ze.getName());
                    File parentDir = f.getParentFile();
                    if (parentDir != null && !parentDir.exists()) {
                        parentDir.mkdirs();
                    }

                    FileOutputStream fout = new FileOutputStream(f);
                    int count;
                    while ((count = zin.read(buffer)) != -1) {
                        fout.write(buffer, 0, count);
                    }
                    zin.closeEntry();
                    fout.close();
                }
            }
            zin.close();
        } catch (IOException e) {
            Log.e(TAG, "unzip: IOException", e);
        }
    }

    private static void dirChecker(String destination, String dir) {
        File f = new File(destination, dir);
        if (!f.isDirectory()) {
            boolean success = f.mkdirs();
            if (!success) {
                Log.w(TAG, "Failed to create folder " + f.getAbsolutePath());
            }
        }
    }
}
