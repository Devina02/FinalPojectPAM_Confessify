package com.example.finalprojectpam_confessify.ui.signup

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalprojectpam_confessify.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    navController: NavHostController
) {
    //buat nyimpen nilai input dari user
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Mendapatkan referensi ke Context lokal
    val context = LocalContext.current

    // Box buat layout utama
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        // Image buat nampilin background
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier
                .width(500.dp)
                .padding(30.dp)
        ) {
            // Column buat menyusun si kayak texfield gitu
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Judul
                Text(
                    text = "Sign Up",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(20.dp))

                // buat input username
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = { Text("Username") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(20.dp))

                // buat inpputin email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(20.dp))

                // Input untuk password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(30.dp))

                // Tombol Daftar
                Button(
                    onClick = {
                        // ini buat manggil fungsi registrasi
                        registerUser(email, username, password)
                        // ini buat nampilin notif kalo udh berhasil regis
                        showToast(context, "Registrasi berhasil!")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Daftar", fontSize = 18.sp)
                }

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Sudah punya akun?",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                // ngasih jarak kebawah
                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    navController.navigate("Login")
                }) {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                }

            }
        }
    }
}

// Fungsi untuk menangani registrasi pengguna
private fun registerUser(email: String, username: String, password: String) {
    val auth = FirebaseAuth.getInstance()

    // Buat pengguna baru dengan email dan password
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Registrasi berhasil
                val user = auth.currentUser

                // Perbarui profil pengguna dengan nama yang diberikan
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build()

                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { profileUpdateTask ->
                        if (profileUpdateTask.isSuccessful) {
                            // jika profil berhasil
                        } else {
                            // Tangani kegagalan pembaruan profil
                        }
                    }
            } else {
                // kalo signup gagal
                val exception = task.exception as? FirebaseAuthException
                // Tangani kegagalan registrasi, tampilkan pesan kesalahan, dll.
            }
        }
}

// Fungsi untuk menampilkan Toast
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
