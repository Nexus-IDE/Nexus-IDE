package com.silva.nexuside.enums

import androidx.annotation.StringRes
import androidx.annotation.StyleRes

import com.silva.nexuside.resources.Strings
import com.silva.nexuside.resources.Styles

enum class UITheme(
    @StringRes var stringResId: Int,
    @StyleRes var styleResId: Int) {
    
    PYRO(Strings.ui_theme_value_pyro, Styles.Theme_Nexus_Pyro),
    INDIGO(Strings.ui_theme_value_indigo, Styles.Theme_Nexus_Indigo),
    FLAMINGO(Strings.ui_theme_value_flamingo, Styles.Theme_Nexus_Flamingo),
    MINT(Strings.ui_theme_value_mint, Styles.Theme_Nexus_Mint),
    ESMERALD(Strings.ui_theme_value_esmerald, Styles.Theme_Nexus_Esmerald);
    
    @StringRes
    fun getTitleResId(): Int {
        return stringResId
    }
    
    @StyleRes
    fun getStyleResId(): Int {
        return styleResId
    }
    
    fun getDefault(): UITheme {
        return PYRO
    }
    
    fun getAvailableList(): List<UITheme> {
         return listOf(*values())
    }
    
    fun getValueOf(name: String): UITheme? {
        for (theme in values()) {
            if (theme.name == name)
                return theme
        }
        return null
    }
}