package com.example.featureflaguser.feature.splash

sealed class SplashUiState {
    data object Loading: SplashUiState()
    data object NavigateToLogin: SplashUiState()
    data object NavigateToDashboard: SplashUiState()
}