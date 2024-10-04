package com.silva.nexuside.project;

import com.silva.nexuside.enums.WizardTemplates;
import java.io.File;

public class Project {
    
    public WizardTemplates mTemplate;
    
    public String mAppName;
    
    public String mPackageName;
    
    public File mAppPath;
    
    public String mLanguage;
    
    public int mMinSdk = 21;
    
    public Project() {
    	
    }
    
    public void setTemplate(WizardTemplates template) {
    	mTemplate = template;
    }
    
    public WizardTemplates getTemplate() {
    	return mTemplate;
    }
    
    public void setAppName(String appName) {
    	mAppName = appName;
    }
    
    public String getAppName() {
    	return mAppName;
    }
    
    public void setPackageName(String packageName) {
    	mPackageName = packageName;
    }
    
    public String getPackageName() {
    	return mPackageName;
    }
    
    public void setPath(File appPath) {
    	mAppPath = appPath;
    }
    
    public File getPath() {
    	return mAppPath;
    }
    
    public void setLanguage(String language) {
    	mLanguage = language;
    }
    
    public String getLanguage() {
    	return mLanguage;
    }
    
    public void setMinSdk(int minSdk) {
    	mMinSdk = minSdk;
    }
    
    public int getMinSdk() {
    	return mMinSdk;
    }
    
}
