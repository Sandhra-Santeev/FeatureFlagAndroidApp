package com.example.featureflaguser.feature.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featureflaguser.core.common.Result
import com.example.featureflaguser.core.network.dto.LoginRequest
import com.example.featureflaguser.core.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){

    private val _loginState = MutableStateFlow<LoginUiState>(
        LoginUiState.Idle
    )
    val loginState: StateFlow<LoginUiState> = _loginState
    fun login(request: LoginRequest){
        viewModelScope.launch {
            if(request.email.isBlank()||request.password.isBlank()){
                _loginState.value = LoginUiState.Error("All fields are mandatory")
                return@launch
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(request.email).matches()) {
                _loginState.value = LoginUiState.Error("Please enter a valid email address")
                return@launch
            }
            _loginState.value = LoginUiState.Loading
            val result = authRepository.login(request)
            when(result){
                is Result.Success ->{
                    _loginState.value = LoginUiState.Success(result.data)
                }

                is Result.Error->{
                    _loginState.value = LoginUiState.Error(result.message)
                }
                else->{

                }

            }

        }
    }
}
