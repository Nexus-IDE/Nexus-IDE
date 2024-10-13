package com.silva.nexuside.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WizardAPILevel {
	
    API16("API 16: Android 4.1 (Jelly Bean)", 16),
    API17("API 17: Android 4.2 (Jelly Bean)", 17),
    API18("API 18: Android 4.3 (Jelly Bean)", 18),
    API19("API 19: Android 4.4 (KitKat)", 19),
    API20("API 20: Android 4.4W (KitKat Watch)", 20),
    API21("API 21: Android 5.0 (Lollipop)", 21),
    API22("API 22: Android 5.1 (Lollipop)", 22),
    API23("API 23: Android 6.0 (Marshmallow)", 23),
    API24("API 24: Android 7.0 (Nougat)", 24),
    API25("API 25: Android 7.1 (Nougat)", 25),
    API26("API 26: Android 8.0 (Oreo)", 26),
    API27("API 27: Android 8.1 (Oreo)", 27),
    API28("API 28: Android 9.0 (Pie)", 28),
    API29("API 29: Android 10 (Q)", 29),
    API30("API 30: Android 11 (R)", 30),
    API31("API 31: Android 12 (SnowCone)", 31),
    API32("API 32: Android 12L (SnowCone)", 32),
    API33("API 33: Android 13 (Tiramisu)", 33),
    API34("API 34: Android 14 (UpsideDownCake)", 34);
    
    private final String description;
    
    private final int apiLevel;
    
    WizardAPILevel(String description, int apiLevel) {
        this.description = description;
        this.apiLevel = apiLevel;
    }
    
    public String getDescription() {
        return description;
    }
    
    
    public int getApiLevel() {
    	return apiLevel;
    }
    
    public static List<WizardAPILevel> getAvailableList() {
        List<WizardAPILevel> list = new ArrayList<>(Arrays.asList(values()));
        return list;
    }
    
    public static WizardAPILevel safeValueOf(String name) {
        for(WizardAPILevel template : values()) {
        	if (template.name().equals(name)) {
                return template;
            }
        }
        return null;
    }
    
}
