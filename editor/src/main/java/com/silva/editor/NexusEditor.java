package com.silva.editor;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import com.silva.editor.autocompletion.JavaLanguage;
import io.github.rosemoe.sora.langs.textmate.TextMateAnalyzer;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import org.eclipse.tm4e.core.registry.IThemeSource;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;

public class NexusEditor extends CodeEditor {
    
    public String path;
    
    public NexusEditor(Context context) {
        super(context);
        init();
    }
    
    public NexusEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public NexusEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    public void init() {
        try {
            applyTheme();
            TextMateColorScheme scheme = TextMateColorScheme.create(ThemeRegistry.getInstance());
        	setColorScheme(scheme);
            TextMateLanguage language = TextMateLanguage.create(GrammarRegistry.getInstance().loadGrammars("editor/languages.json").get(0).getScopeName(), true);
            language.setAutoCompleteEnabled(true);
            //setEditorLanguage(new JavaLanguage(this, getPath()));
            setTypefaceText(Typeface.MONOSPACE);
            setWordwrap(true);
            getProps().symbolPairAutoCompletion = true;
            getComponent(EditorAutoCompletion.class).setEnabled(true);
        } catch(Exception err) {
            Log.e("NexusEditor", err.toString());
        	err.printStackTrace();
        }
    }
    
    public void applyTheme() {
		FileProviderRegistry.getInstance()
		.addFileProvider(new AssetsFileResolver(getContext().getAssets()));
		ThemeRegistry scheme = ThemeRegistry.getInstance();
		String light = "editor/darcula.json";
		for (String theme : new String[] { light}) {
			String path =  theme;
			try {
				scheme.loadTheme(
				new ThemeModel(
				IThemeSource.fromInputStream(
				FileProviderRegistry.getInstance().tryGetInputStream(path),
				path,
	            null),
				theme));
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		scheme.setTheme(light);
	}
    
    public void setPath(String path) {
    	this.path = path;
    }
    
    public String getPath() {
    	return path;
    }
    
}
