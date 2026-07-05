package com.example.featureflaguser.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.featureflaguser.core.network.dto.LoginRequest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        if(loginState is LoginUiState.Success){

            navController.navigate("dashboard"){
                popUpTo("splash"){
                    inclusive = true
                }
            }

        }
        if (loginState is LoginUiState.Error){
            Toast.makeText(context,(loginState as LoginUiState.Error).message , Toast.LENGTH_SHORT).show()
        }
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(loginState){

            is LoginUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            is LoginUiState.Error ->{

            }

            else -> {

            }

        }
        Text( text = "Login", fontSize = 20.sp)
        Spacer(modifier= Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier= Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(modifier= Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            onClick = {
                val request = LoginRequest(
                    email,
                    password
                )
                viewModel.login(request)

            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = _root_ide_package_.androidx.compose.ui.graphics.Color.White,
                containerColor = _root_ide_package_.androidx.compose.ui.graphics.Color.Blue

            )

        ) {
            Text("Login")
        }
    }


}