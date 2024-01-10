package com.example.finalprojectpam_confessify.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalprojectpam_confessify.R
import com.example.finalprojectpam_confessify.SplashScreen
import com.example.finalprojectpam_confessify.ui.Create.CreateScreen
import com.example.finalprojectpam_confessify.ui.Home.HomeScreen
import com.example.finalprojectpam_confessify.ui.akunprofil.AkunProfil
import com.example.finalprojectpam_confessify.ui.login.LoginScreen
import com.example.finalprojectpam_confessify.ui.signup.SignUpScreen


@Composable
fun Nav(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "SplashScreen" ){
        composable("SplashScreen"){
            SplashScreen(navController)
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
            AkunProfil(navController)
        }
        composable("Create"){
            CreateScreen(navController)
        }

    }
}

