package com.silva.nexuside.settings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva.nexuside.settings.repositories.PreferencesRepository
import kotlinx.coroutines.launch

class PreferencesViewModel(private val repo: PreferencesRepository) : ViewModel() {

    val uiMode = repo.uiMode

    fun changeUiMode(value: Int) {
        viewModelScope.launch { repo.changeUiMode(value) }
    }
}
