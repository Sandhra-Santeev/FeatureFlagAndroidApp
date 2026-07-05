package com.example.featureflaguser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.featureflaguser.feature.dashboard.DashboardScreen
import com.example.featureflaguser.feature.login.LoginScreen
import com.example.featureflaguser.feature.splash.SplashScreen
import com.example.featureflaguser.ui.theme.FeatureFlagUserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeatureFlagUserTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
               val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ){
                    composable("splash"){
                        SplashScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("dashboard") {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FeatureFlagUserTheme {
        Greeting("Android")
    }
}