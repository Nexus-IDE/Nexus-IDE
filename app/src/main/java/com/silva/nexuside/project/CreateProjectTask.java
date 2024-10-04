package com.silva.nexuside.project;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.silva.util.Decompress;
import com.silva.util.FileUtil;
import java.io.File;

@Deprecated
public class CreateProjectTask extends AsyncTask<Project, String, String> {
    
    private Context context;
    
    private Project project;
    
    public CreateProjectTask(Context context) {
    	this.context = context;
    }
    
    @Override
    public void onPreExecute() {
    	
    }
    
    @Override
    public String doInBackground(Project... params) {
        project = params[0];
        try {
            if(!project.getPath().exists()) {
            	project.getPath().mkdir();
            }
            
            Decompress.unzipFromAssets(context, project.getTemplate().getPath(), project.getPath().getAbsolutePath());
            
            File languagePath = new File(project.getPath(), project.getTemplate().getName() + "/" + project.getLanguage());
            
            FileUtil.copyDir(languagePath.getAbsolutePath(), project.getPath().getAbsolutePath());
            FileUtil.deleteFile(project.getPath() + "/" + project.getTemplate().getName());
            
            File manifestFile = new File(project.getPath(), "app/src/main/AndroidManifest.xml");
            
            File javaDir = new File(project.getPath(), "app/src/main/java/$" + "packagename" + "/");
            
            javaDir.renameTo(new File(project.getPath(), "app/src/main/java/" + project.getPackageName().replace(".", "/") + "/"));
            
            javaDir = new File(project.getPath(), "app/src/main/java/" + project.getPackageName().replace(".", "/") + "/");
            
            File javaFile = new File(javaDir, "MainActivity.java");
            
            String javaFileStr = FileUtil.readFile(new File(project.getPath(), "app/src/main/java/$" + "packagename" + "/MainActivity.java").getAbsolutePath());
            javaFileStr = javaFileStr.replace("package $" + "packagename", "package " + project.getPackageName());
            FileUtil.writeFile(javaFile.getAbsolutePath(), javaFileStr);
            FileUtil.deleteFile(new File(project.getPath(), "app/src/main/java/$" + "packagename" + "/").getAbsolutePath());
            
            File valuesFile = new File(project.getPath(), "app/src/main/res/values/strings.xml");
            String valuesFileStr = FileUtil.readFile(valuesFile.getAbsolutePath());
            valuesFileStr = valuesFileStr.replace(">$" + "appname", ">" + project.getAppName());
            FileUtil.writeFile(valuesFile.getAbsolutePath(), valuesFileStr);
            
            File buildFile = new File(project.getPath(), "app/build.gradle");
            String buildFileStr = FileUtil.readFile(buildFile.getAbsolutePath());
            buildFileStr = buildFileStr.replace("$"+"targetSdkVersion", "34").replace("$"+"minSdkVersion", String.valueOf(project.getMinSdk())).replace("$" + "packagename", project.getPackageName());
            FileUtil.writeFile(buildFile.getAbsolutePath(), buildFileStr);
            
            File settingsFile = new File(project.getPath(), "settings.gradle");
            String settingsFileStr = FileUtil.readFile(settingsFile.getAbsolutePath());
            settingsFileStr = settingsFileStr.replace("$appname", project.getAppName());
            FileUtil.writeFile(settingsFile.getAbsolutePath(), settingsFileStr);
        } catch(Exception err) {
        	return "error: " + err.getMessage();
        }
    	return "success";
    }
    
    @Override
    public void onProgressUpdate(String... update) {
    	
    }
    
    @Override
    public void onPostExecute(String result) {
    	if(result.contains("error")) {
    		Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    	} else Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
    
}
