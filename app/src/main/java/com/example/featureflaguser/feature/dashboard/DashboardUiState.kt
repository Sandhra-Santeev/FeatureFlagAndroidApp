package com.example.featureflaguser.feature.dashboard


import com.example.featureflaguser.feature.dashboard.model.FeatureEvaluationResponse

sealed class DashboardUiState {
    data object Idle: DashboardUiState()
    data object Loading: DashboardUiState()
    data class Success(
        val feature: FeatureEvaluationResponse
    ): DashboardUiState()
    data class Error(
        val message: String
    ): DashboardUiState()
}