package com.example.finalprojectpam_confessify.ui.akunprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpam_confessify.Greeting
import com.example.finalprojectpam_confessify.R
import com.example.finalprojectpam_confessify.ui.theme.FinalProjectPAM_ConfessifyTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// Layar ini bertanggung jawab untuk menampilkan profil pengguna.
@Composable
fun AkunProfilScreen(navController: NavHostController) {
    // Mengambil instance Firebase Auth untuk mengelola otentikasi pengguna.
    val auth = FirebaseAuth.getInstance()
    // Mendapatkan pengguna yang saat ini masuk.
    val user = auth.currentUser

    // Tampilan utama menggunakan Box sebagai kontainer paling luar.
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Gambar latar belakang menggunakan Image dengan ContentScale Crop.
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Column utama yang mengandung komponen-komponen lainnya.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            // Card untuk mengelompokkan elemen-elemen tampilan.
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                // Column di dalam Card untuk tata letak vertikal.
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Gambar profil pengguna dengan bentuk lingkaran.
                    Image(
                        painter = painterResource(id = R.drawable.profil),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(shape = CircleShape)
                    )

                    // Spacer untuk memberikan ruang antara elemen-elemen.
                    Spacer(modifier = Modifier.height(40.dp))

                    // Menampilkan data pengguna menggunakan komponen UserProfile.
                    UserProfile(user?.displayName, user?.email)

                    // Tombol "Edit Username" untuk navigasi ke layar EditProfil.
                    Button(
                        onClick = {
                            println("Navigating to EditUsername")
                            navController.navigate("UpdateProfil")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Edit Username")
                    }

                    // Tombol "LogOut Akun" untuk keluar dari akun pengguna.
                    Button(onClick = {
                        auth.signOut()
                        navController.navigate("Login")
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "LogOut Akun")
                    }
                }
            }
        }

        // Row di bagian bawah untuk menampilkan tombol navigasi ke layar lain.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Tombol "Create" untuk navigasi ke layar Create.
            Button(
                onClick = {
                    println("Navigating to Create")
                    navController.navigate("Create")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create"
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Create")
            }

            // Tombol "Home" untuk navigasi ke layar Home.
            Button(
                onClick = {
                    navController.navigate("Home")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Home")
            }

            // Tombol "Profile" untuk kembali ke layar AkunProfil.
            Button(
                onClick = { navController.navigate("AkunProfil") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Profile"
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Profile")
            }
        }
    }
}

// Komponen untuk menampilkan informasi pengguna.
@Composable
fun UserProfile(username: String?, email: String?) {
    // Column untuk tata letak vertikal elemen-elemen UserProfile.
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Menampilkan nama pengguna dengan format tertentu.
        Text(
            text = "Username  : ${username ?: "N/A"}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp),
            color = Color.Black
        )
        // Menampilkan alamat email pengguna dengan format tertentu.
        Text(
            text = "Email  : ${email ?: "N/A"}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp),
            color = Color.Black
        )
    }
}
