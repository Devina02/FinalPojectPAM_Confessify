package com.example.finalprojectpam_confessify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalprojectpam_confessify.SplashScreen
import com.example.finalprojectpam_confessify.ui.Create.CreateScreen
import com.example.finalprojectpam_confessify.ui.Home.HomeScreen
import com.example.finalprojectpam_confessify.ui.akunprofil.AkunProfilScreen
import com.example.finalprojectpam_confessify.ui.login.LoginScreen
import com.example.finalprojectpam_confessify.ui.signup.SignUpScreen


@Composable
fun Nav(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "SplashScreen" ){
        composable("SplashScreen"){
            SplashScreen(navController = navController)
        }
        composable("SignUp"){
            SignUpScreen(navController)
        }
        composable("Login"){
            LoginScreen(navController)
        }
        composable("Home"){
            HomeScreen(navController)
        }
        composable("AkunProfil"){
            AkunProfilScreen(navController)
        }
        composable("Create"){
            CreateScreen(navController)
        }

    }
}

