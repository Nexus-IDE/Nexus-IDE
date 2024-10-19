package com.silva.nexuside.core.settings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

import com.silva.nexuside.core.settings.repositories.PreferencesRepository

class PreferencesViewModel(
    private val repo: PreferencesRepository
) : ViewModel() {

    val uiMode = repo.uiMode
    
    fun changeUiMode(value: Int) {
        viewModelScope.launch {
            repo.changeUiMode(value) 
        }
    }
    
}
