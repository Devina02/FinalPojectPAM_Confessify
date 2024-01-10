package com.example.finalprojectpam_confessify.ui.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalprojectpam_confessify.R
import com.example.finalprojectpam_confessify.ui.signup.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(
    navController: NavHostController

) {
    // buat nyimpen email dan password yang diinput oleh pengguna
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Mendapatkan referensi ke Context lokal
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium).width(500.dp)
                .padding(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(40.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_lock_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        // buat manggil fungsi login
                        loginUser(email, password, navController, context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ){
                    Text("Login", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Belum punya akun?",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(onClick = { navController.navigate("SignUp")}){
                    Text(
                        text = "SignUp",
                        fontSize = 18.sp,
                        color = Color.Black
                        )
                }
            }
        }
    }
}

// Fungsi untuk melakukan login pengguna
private fun loginUser(email: String, password: String, navController: NavHostController,context: Context) {
    // Mendapatkan instance Firebase Authentication
    val auth = FirebaseAuth.getInstance()

    // Melakukan login dengan email dan password menggunakan Firebase Authentication
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(context, "Login berhasil!")
                // Navigasi ke layar "Home"
                navController.navigate("Home")
            } else {
                val exception = task.exception as? FirebaseAuthException
                // ini buat nampilin pesan kalo login gagal
                showToast(context, "Login gagal. ${exception?.message}")
            }
        }
}
