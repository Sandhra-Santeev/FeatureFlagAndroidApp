package com.example.featureflaguser.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featureflaguser.core.common.Result
import com.example.featureflaguser.feature.dashboard.repository.FeatureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: FeatureRepository
): ViewModel(){
    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Idle)
    val uiState: StateFlow<DashboardUiState> = _uiState
    fun evaluateFeature(key: String){
        viewModelScope.launch {
            if(key.isBlank()){
                _uiState.value = DashboardUiState.Error("Please enter a feature key")
                return@launch
            }
            _uiState.value = DashboardUiState.Loading
            val result = repository.evaluateFeature(key)
            when(result){
                is Result.Success->_uiState.value = DashboardUiState.Success(result.data)
                is Result.Error -> _uiState.value = DashboardUiState.Error(result.message)
                else->{

                }
            }
        }
    }


}
