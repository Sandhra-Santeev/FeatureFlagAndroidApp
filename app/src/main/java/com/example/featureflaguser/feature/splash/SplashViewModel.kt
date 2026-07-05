package com.example.featureflaguser.feature.splash

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featureflaguser.core.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState
    init {
        viewModelScope.launch {
            val token = dataStoreManager.getToken().first()
            if(!token.isNullOrBlank()){
                _uiState.value = SplashUiState.NavigateToDashboard
            }else{
                _uiState.value = SplashUiState.NavigateToLogin
            }
        }
    }
}