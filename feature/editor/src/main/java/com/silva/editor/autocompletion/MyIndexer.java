package com.silva.editor.autocompletion;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.common.collect.ImmutableMap;
import com.silva.util.FileUtil;
import com.tyron.javacompletion.JavaCompletions;
import com.tyron.javacompletion.options.JavaCompletionOptionsImpl;
import com.tyron.javacompletion.tool.Indexer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.tyron.javacompletion.file.FileManager;
import com.tyron.javacompletion.file.PathUtils;
import com.tyron.javacompletion.file.SimpleFileManager;
import com.tyron.javacompletion.model.FileScope;
import com.tyron.javacompletion.model.Module;
import com.tyron.javacompletion.options.IndexOptions;
import com.tyron.javacompletion.parser.AstScanner;
import com.tyron.javacompletion.parser.ParserContext;
import com.tyron.javacompletion.parser.classfile.ClassModuleBuilder;
import com.tyron.javacompletion.project.Project;
import com.tyron.javacompletion.project.SimpleModuleManager;
import com.tyron.javacompletion.storage.IndexStore;
import java.util.zip.ZipOutputStream;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class MyIndexer extends Indexer {
	private int isIndexing=0;
	private String[] indexes;
	private String path;
	private JavaCompletions javaCompletions;
	
	public MyIndexer(){
		javaCompletions = new JavaCompletions();
	}
	
	public JavaCompletions getJavaCompletions(){
		return javaCompletions;
	}
	
	public boolean isIndexing(){
		return isIndexing>0;
	}
	
	private final ParserContext parserContext = new ParserContext();
	
	public String[] getIndexsFiles(){
		return indexes;
	}
	
	public MyIndexer indexFiles(Context context){
		//if(isIndexing) return null;
		isIndexing++;
		String data = FileUtil.getPackageDataDir(context);
		try {
		ArrayList<String> jars=new ArrayList<>();
		String index=data+"/bin/index.json";
		String index2=data+"/bin/index2.json";
            
		/*if(!FileUtil.isExistFile(idx1)){
			Utils.unzipf(data+"/bin/cp.jar",data+"/bin/files/","");
			jars.add(data+"/bin/cp.jar");
			run(jars,idx1,Collections.emptyList(),Collections.emptyList(),data+"/bin/files/");
			FileUtil.deleteFile(data+"/bin/files/");
			jars.clear();
		}*/
		
		if((!FileUtil.isExistFile(index))||new java.io.File(index).length()==0){
		FileUtil.writeFile(index,"");
		extractAssetFile(context,"java/libs.jar",data+"/bin/addition.jar");
		unzipf(data+"/bin/addition.jar",data+"/bin/add/","");
		jars.add(data+"/bin/add/");
		run(jars,index,Collections.emptyList(),Collections.emptyList(),data+"/bin/add/");
		FileUtil.deleteFile(data+"/bin/add/");
		jars.clear();
		}
		
		/*if((!FileUtil.isExistFile(idx4))||new java.io.File(idx4).length()==0){
			FileUtil.writeFile(idx4,"");
			Utils.extractAssetFile(editor.getContext(),"editor/libgdx.json",idx4);
		}
		*/
		
		jars.add(data+"/java/");
		run(jars,index2,Collections.emptyList(),Collections.emptyList(),data+"/java/");
		indexes = new String[]{index,index2};
		} catch(Exception exception){
			FileUtil.writeFile(data+"/error.txt","Failed To Index Files : \n"+Log.getStackTraceString(exception));
			return this;
		}
		
		ArrayList<String> list=new ArrayList<>();
		
		String logPath=data+"/log/autocomplete.log";
		if(!FileUtil.isExistFile(logPath)) FileUtil.writeFile(logPath,"");
		JavaCompletionOptionsImpl options = new JavaCompletionOptionsImpl(
		logPath,
		Level.ALL,
		java.util.Collections.emptyList(),
		list/*indexed files list...*/
		);
		
		javaCompletions.initialize(URI.create("file://" + data+"/java/"),options);
		try {
			IndexUtil.loadJdk(javaCompletions.getProject(),context,indexes);
			} catch(Exception ex){
			Log.e("jdk_error",Log.getStackTraceString(ex));
		}
		isIndexing--;
		return this;
	}
	
	public void getClasses(ArrayList<String> arrayList,String path){
		ArrayList<String> list=new ArrayList<>();
		FileUtil.listDir(path,list);
		for(String file:list)
			if(FileUtil.isDirectory(file))
				getClasses(arrayList,file);
					else if(file.endsWith(".class")||file.endsWith(".java"))
						arrayList.add(file);
	}
	
	public void run(
	List<String> inputPaths,
	String outputPath,
	List<String> ignorePaths,
	List<String> dependIndexFiles,
	String root) {
		Path rootPath = new java.io.File(root).toPath();
		// Do not initialize the project. We handle the files on our own.
		SimpleModuleManager moduleManager = new SimpleModuleManager();
		Project project = new Project(moduleManager, moduleManager.getFileManager());
		for (String inputPath : inputPaths) {
			Path path = Paths.get(inputPath);
			// Do not use module manager's file manager because we need to setup root
			// path and ignore paths per directory.
			FileManager fileManager = new SimpleFileManager(path, ignorePaths);
			ClassModuleBuilder classModuleBuilder = new ClassModuleBuilder(moduleManager.getModule());
			ImmutableMap<String, Consumer<Path>> handlers =
			ImmutableMap.<String, Consumer<Path>>of(
			".class",
			classModuleBuilder::processClassFile,
			".java",
			subpath ->{addJavaFile(subpath, moduleManager.getModule(), fileManager);});
			if (Files.isDirectory(path)) {
				System.out.println("Indexing directory: " + inputPath);
				PathUtils.walkDirectory(
				path,
				handlers,
				/* ignorePredicate= */ fileManager::shouldIgnorePath);
				} else if (inputPath.endsWith(".jar") || inputPath.endsWith(".srcjar")) {
				System.out.println("Indexing JAR file: " + inputPath);
				try {
					PathUtils.walkDirectory(rootPath
					/*PathUtils.getRootPathForJarFile(path)*/,
					handlers,
					/* ignorePredicate= */ subpath -> false);
					} catch (Exception t) {
					throw new RuntimeException(t);
				}
			}
		}
		for (String dependIndexFile : dependIndexFiles) {
			project.loadTypeIndexFile(dependIndexFile);
		}
		System.out.println("Writing index file to " + outputPath);
		new IndexStore().writeModuleToFile(moduleManager.getModule(), Paths.get(outputPath));
	}
	
	private void addJavaFile(Path path, Module module, FileManager fileManager) {
		Optional<CharSequence> content = fileManager.getFileContent(path);
		if (content.isPresent()) {
			FileScope fileScope =
			new AstScanner(IndexOptions.NON_PRIVATE_BUILDER.build())
			.startScan(
			parserContext.parse(path.toString(), content.get()),
			path.toString(),
			content.get());
			module.addOrReplaceFileScope(fileScope);
		}
	}
    
    public static void extractAssetFile(Context context,String file,String to) throws Exception {
			if(!FileUtil.isExistFile(to)) FileUtil.writeFile(to,"");
			int count;
			java.io.InputStream input= context.getAssets().open(file);
			java.io.OutputStream output = new  java.io.FileOutputStream(to);
			byte data[] = new byte[1024];
			while ((count = input.read(data))>0) {
				output.write(data, 0, count);
			}
			output.flush();
			output.close();
			input.close();
	}
    
    public static void unzipf(String path,String to,String password) throws Exception {
			if(new ZipFile(path).isEncrypted()) new ZipFile(path,password.toCharArray()).extractAll(to);
				else new ZipFile(path).extractAll(to);
		}
	
}