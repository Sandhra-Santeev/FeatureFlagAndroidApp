package com.example.featureflaguser.feature.login

import com.example.featureflaguser.core.network.dto.LoginResponse

sealed class LoginUiState(){
    data object Idle: LoginUiState()
    data object Loading: LoginUiState()
    data class Success(
        val loginResponse: LoginResponse
    ): LoginUiState()
    data class  Error(
        val message:String
    ): LoginUiState()
}
