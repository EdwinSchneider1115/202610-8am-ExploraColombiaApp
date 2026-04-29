package com.edwingomez.exploraapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwingomez.exploraapp.ui.elements.AddTouristicPlaceScreen
import com.edwingomez.exploraapp.ui.elements.HomeScreen
import com.edwingomez.exploraapp.ui.elements.LoginScreen
import com.edwingomez.exploraapp.ui.elements.RegisterScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myNavController = rememberNavController()

            NavHost(
                navController = myNavController,
                startDestination = "login",
                modifier = Modifier.fillMaxSize()
            ) {

                composable(route = "login") {
                    LoginScreen(
                        onLoginSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            myNavController.navigate("register")
                        }
                    )
                }
                composable(route = "register") {
                    RegisterScreen(
                        onRegisterSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            myNavController.navigate("login")
                        },
                        onBackClick = {
                            myNavController.popBackStack()
                        }
                    )
                }
                composable(route = "home") {
                    HomeScreen(

                    )
                }
                composable(route="add_touristic_place"){
                    AddTouristicPlaceScreen()
                }
            }
        }
    }
}
