package com.example.featureflaguser.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.featureflaguser.feature.login.LoginViewModel
import androidx.compose.runtime.getValue

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState) {
        when(uiState){
            SplashUiState.NavigateToDashboard->{
                navController.navigate("dashboard"){
                    popUpTo("splash"){
                        inclusive = true
                    }
                    launchSingleTop = true
                }

            }
            SplashUiState.NavigateToLogin->{
                navController.navigate("login"){
                    popUpTo("splash"){
                        inclusive = true
                    }
                    launchSingleTop = true
                }

            }
            SplashUiState.Loading->Unit
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}