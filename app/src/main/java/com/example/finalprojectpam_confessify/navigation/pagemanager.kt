package com.example.finalprojectpam_confessify.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalprojectpam_confessify.SplashScreen
import com.example.finalprojectpam_confessify.ui.Create.CreateScreen
import com.example.finalprojectpam_confessify.ui.Home.HomeScreen
import com.example.finalprojectpam_confessify.ui.akunprofil.AkunProfilScreen
import com.example.finalprojectpam_confessify.ui.akunprofil.UpdateProfile
import com.example.finalprojectpam_confessify.ui.login.LoginScreen
import com.example.finalprojectpam_confessify.ui.signup.SignUpScreen
import com.google.firebase.firestore.FirebaseFirestore



// Ini bagian yang ngatur perpindahan antar layar dalam aplikasi.
// Pake NavHost buat nyimpen daftar layar dan arahin pengguna ke layar-layar berbeda.
// `navController` tuh kendali navigasi buat ngirim pengguna ke layar selanjutnya.
// `firestore` tuh objek Firestore dari Firebase yang dipake di beberapa layar.
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(navController: NavHostController, firestore: FirebaseFirestore) {

    // NavHost ini semacam kotak buat nyimpen daftar komposisi layar yang bisa diakses pengguna.
    // `startDestination` itu nentuin layar pertama yang muncul kalo aplikasi dibuka.
    NavHost(navController = navController, startDestination = "SplashScreen" ){
        // composable ini nyiapin tampilan buat layar tertentu.
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
            // HomeScreen ini layar utama aplikasi yang nunjukin konten utama buat pengguna.
            // Parameter `firestore` dipake buat nyampe dan ubah data dari Firebase Firestore.
            HomeScreen(navController, firestore = firestore)
        }
        composable("AkunProfil"){
            // AkunProfilScreen nunjukin info profil pengguna, mungkin juga terkoneksi ke Firebase Authentication.
            AkunProfilScreen(navController)
        }
        composable("Create"){
            // CreateScreen buat ngizinin pengguna bikin konten baru, misalnya pengakuan atau postingan.
            CreateScreen(navController)
        }
        composable("UpdateProfil"){
            // UpdateProfile nyediain antarmuka buat ganti info profil pengguna.
            UpdateProfile(navController)
        }
    }
}
