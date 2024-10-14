package com.silva.nexuside.enums;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import com.silva.nexuside.resources.Strings;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WizardTemplates {
	
    EMPTY("templates/icons/empty_project.png", Strings.wizard_templates_empty, "templates/Empty.zip", "Empty Activity"),
    ANDROIDX("templates/icons/androidx_project.png", Strings.wizard_templates_androidx, "templates/AndroidX.zip", "Androidx Activity"),
    LIBGDX("templates/icons/game_project.png", Strings.wizard_templates_game, "templates/LibGDX.zip", "Android Game");
    
    private final String iconPath;
    
    @StringRes
    private final int titleResId;
    
    private String path;
    
    private String name;
    
    WizardTemplates(String iconPath, @StringRes int titleResId, String path, String name) {
        this.iconPath = iconPath;
        this.titleResId = titleResId;
        this.path = path;
        this.name = name;
    }
    
    public String getIconPath() {
        return iconPath;
    }
    
    @StringRes
    public int getTitleResId() {
    	return titleResId;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getName() {
        return name;
    }
    
    public static List<WizardTemplates> getAvailableList() {
        List<WizardTemplates> list = new ArrayList<>(Arrays.asList(values()));
        return list;
    }
    
    public static WizardTemplates safeValueOf(String name) {
        for(WizardTemplates template : values()) {
        	if (template.name().equals(name)) {
                return template;
            }
        }
        return null;
    }
    
}
