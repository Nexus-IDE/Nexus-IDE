package com.silva.editor.autocompletion;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.silva.util.FileUtil;
import com.silva.editor.NexusEditor;
import com.silva.editor.autocompletion.IndexUtil;
import com.silva.editor.autocompletion.MyIndexer;
import com.tyron.javacompletion.JavaCompletions;
import com.tyron.javacompletion.completion.CompletionCandidate;
import com.tyron.javacompletion.completion.CompletionCandidateListBuilder;
import com.tyron.javacompletion.completion.CompletionResult;
import com.tyron.javacompletion.completion.Completor;
import com.tyron.javacompletion.completion.SimpleCompletionCandidate;
import com.tyron.javacompletion.parser.FileContentFixer;
import io.github.rosemoe.sora.event.EventReceiver;
import io.github.rosemoe.sora.event.Unsubscribe;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.lang.completion.Comparators;
import io.github.rosemoe.sora.lang.completion.CompletionHelper;
import io.github.rosemoe.sora.lang.completion.CompletionItem;
import io.github.rosemoe.sora.lang.completion.CompletionItemKind;
import io.github.rosemoe.sora.lang.completion.CompletionPublisher;
import io.github.rosemoe.sora.lang.completion.Filters;
import io.github.rosemoe.sora.lang.completion.FuzzyScore;
import io.github.rosemoe.sora.lang.completion.FuzzyScoreOptions;
import io.github.rosemoe.sora.lang.completion.SimpleCompletionItem;
import io.github.rosemoe.sora.lang.completion.SimpleSnippetCompletionItem;
import io.github.rosemoe.sora.lang.completion.SnippetDescription;
import io.github.rosemoe.sora.lang.completion.snippet.CodeSnippet;
import io.github.rosemoe.sora.lang.completion.snippet.parser.CodeSnippetParser;
import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.ContentIO;
import io.github.rosemoe.sora.text.ContentReference;
import io.github.rosemoe.sora.util.MyCharacter;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.Locale;

public class CodeCompletionHelper implements EventReceiver<SelectionChangeEvent> {
	
	final ArrayList<CompletionItem> items=new ArrayList<>();
    
	final ArrayList<KeywordsHolder> keywords= new ArrayList<>();
    
	final HashMap<String,Drawable> drawablesMap=new HashMap<>();
    
	public boolean proAutoCompletion=true;
    
	private final Path path;
    
	private final JavaCompletions completions;
    
	private final CodeEditor editor;
    
    private Content mContent;
    
    private final static CodeSnippet FOR_SNIPPET = CodeSnippetParser.parse("for(int ${1:i} = 0;$1 < ${2:count};$1++) {\n    $0\n}");
    
    private final static CodeSnippet STATIC_CONST_SNIPPET = CodeSnippetParser.parse("private final static ${1:type} ${2/(.*)/${1:/upcase}/} = ${3:value};");
    
    private final static CodeSnippet CLIPBOARD_SNIPPET = CodeSnippetParser.parse("${1:${CLIPBOARD}}");
    
    private final static CodeSnippet TRY_CATCH_SNIPPET = CodeSnippetParser.parse("try {\n    $0\n} catch(Exception ${1:err}) {\n    $0\n}");
	
	public CodeCompletionHelper(String file,NexusEditor codeEditor){
		path = new java.io.File(file).toPath();
		completions = IndexUtil.getIndexer().getJavaCompletions();
		codeEditor.subscribeEvent(SelectionChangeEvent.class,this);
		/*
		IndexOptions indexOptions=new IndexOptions(){
			@Override
			public boolean shouldIndexPrivate() {
			    return true;
			}

			@Override
			public boolean shouldIndexMethodContent() {
			    return true;
			}
			
		};
		*/
		//moduleManager = new FileSystemModuleManager(completions.getFileManager(),new java.io.File(Editor.getCurrentEditor().getProject().get("java")).toPath(),indexOptions);
		
		//Project project = Editor.getCurrentEditor().getProject();
		editor = codeEditor;
		try {
		//moduleManager.initialize();
		//completions.openFile(path, FileUtil.readFile(file));
		completions.getFileManager().openFileForSnapshot(getURI(file),/*completions.getFileManager().getFileContent(path).orElse(*/FileUtil.readFile(file)/*).toString()*/);
		/*if(Editor.getCurrentEditor()!=null)
			for(int x=0;x<Editor.getCurrentEditor().getChildCount();x++){
				View v=Editor.getCurrentEditor().getChildAt(x);
				if(v instanceof ImageView&&v instanceof EditorItem){
					String name=((EditorItem)v).getPropertySet().getString("name");
					drawablesMap.put(name,((ImageView)v).getDrawable());
				}
			}*/
		} catch(Exception exception){}
	}
	public void add(String keyword,String type){
		for(KeywordsHolder holder:keywords){
			if(holder.type.equals(type)){
				holder.keywords.add(keyword);
				return;
			}
		}
		KeywordsHolder holder = new KeywordsHolder(type,new ArrayList<>());
		holder.keywords.add(keyword);
		keywords.add(holder);
	}
	
	public static URI getURI(String file){
		return URI.create("file://"+file);
	}

  public void requireAutoComplete(
        ContentReference content,
        String line,
        CharPosition position,
        String prefix,
        CompletionPublisher completionPublisher,
        Bundle extraArguments) {
        
     completions.updateFileContent(path,content.toString());  
        
    /*String text = editor.getText().toString();
    String prefix = getPrefix(text, charPosition.line, charPosition.column);
    boolean prefixStartWithLycris = prefix.matches("^[a-zA-Z].*");
        
    if(!prefixStartWithLycris) {
    	return;
    }*/
        
   if(prefix.matches("^[a-zA-Z].*")||line.endsWith(".")) {
   	CompletionResult result = completions.getCompletions(path,position.getLine(),position.getColumn());
	   //CompletionResult result = new Completor(completions.getFileManager()).getCompletionResult(null,path,charPosition.line,charPosition.column);
 	
      for (CompletionCandidate candidate : result.getCompletionCandidates()) {
        if (!"<error>".equals(candidate.getName())) {
            CompletionItem item = getCompletion(candidate.getName(),candidate.getDetail().orElse(candidate.getKind().name()),prefix,candidate.getKind());
            completionPublisher.addItem(item);
	    }
      }
     Log.e("completion_pos",position.getIndex()+","+position.getColumn());
    }
        
    if("try".startsWith(prefix) && prefix.length() > 0) {
            completionPublisher.addItem(new SimpleSnippetCompletionItem("try-catch", "A try-catch statement", new SnippetDescription(prefix.length(), TRY_CATCH_SNIPPET, true)));
    
    }
    if("for".startsWith(prefix) && prefix.length() > 0) {
            completionPublisher.addItem(new SimpleSnippetCompletionItem("for", "For loop on index", new SnippetDescription(prefix.length(), FOR_SNIPPET, true)));
    
    }
    if("clip".startsWith(prefix) && prefix.length() > 0) {
            completionPublisher.addItem(new SimpleSnippetCompletionItem("clipboard", "A copy to clipboard statement", new SnippetDescription(prefix.length(), CLIPBOARD_SNIPPET, true)));
    
    }
    if("static".startsWith(prefix) && prefix.length() > 0) {
            completionPublisher.addItem(new SimpleSnippetCompletionItem("static", "A static constant", new SnippetDescription(prefix.length(), STATIC_CONST_SNIPPET, true)));
    
    }
   }
	private CompletionItemKind getKind(CompletionCandidate.Kind candKind){
		CompletionItemKind kind;
		switch (candKind) {
			case CLASS:
			kind = CompletionItemKind.Class;
			break;
			case INTERFACE:
			kind = CompletionItemKind.Interface;
			break;
			case ENUM:
			kind = CompletionItemKind.Enum;
			break;
			case METHOD:
			kind = CompletionItemKind.Method;
			break;
			case FIELD:
			kind = CompletionItemKind.Field;
			break;
			case VARIABLE:
			kind = CompletionItemKind.Variable;
			break;
			case PACKAGE:
			kind = CompletionItemKind.Module;
			break;
			case KEYWORD:
			kind = CompletionItemKind.Keyword;
			break;
			default:
			kind = CompletionItemKind.Text;
			break;
		}
		return kind;
	}
	
	private boolean checkAggressive(FuzzyScore fuzzyScore,String word,String keyword){
		if(keyword.toLowerCase().startsWith(word.toLowerCase())) return true;
		return (fuzzyScore!=null&&fuzzyScore.getScore() < -20);
	}
	
	private class Checker implements CompletionHelper.PrefixChecker {
		@Override
		public boolean check(char c) {
			return MyCharacter.isJavaIdentifierPart(c);
		}
	}
	
	private CompletionItem getCompletion(String keyword,String type,String prefix,CompletionCandidate.Kind kind){
        CompletionItem completionItem = new JavaCompletionItem(keyword,type,prefix,keyword);
          if(/*type.equals("PlayerItem")&&*/drawablesMap.containsKey(keyword)){
		    	return completionItem.icon(drawablesMap.get(keyword));
      	}
	  	return completionItem.kind(kind==null?CompletionItemKind.Keyword:getKind(kind));
	}
	
	private class KeywordsHolder {
		public final String type;
		public final ArrayList<String> keywords;
		public KeywordsHolder(String tp,ArrayList<String> ks){
			type = tp;
			keywords = ks;
		}
	}
	@Override
	public void onReceive(SelectionChangeEvent arg0, Unsubscribe arg1) {
		completions.updateFileContent(path,editor.getText().toString());
	}
	
}