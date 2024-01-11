package com.example.finalprojectpam_confessify.ui.akunprofil

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpam_confessify.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

// Layar ini urusannya buat update profil pengguna.
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfile(navController: NavHostController) {
    // State ini buat simpan nilai dari TextField.
    var updateProfile by remember { mutableStateOf("") }

    // Dapetin konteks lokal.
    val context = LocalContext.current

    // Tampilan utama pake Box sebagai tempatnya.
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Gambar latar pake Image dengan ContentScale Crop.
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Column utama isinya komponen-komponen lain.
        Column(
            modifier = Modifier
                .fillMaxWidth().align(Alignment.Center)
        ) {
            // Card buat ngegabungin elemen-elemen tampilan.
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                // Column di dalam Card buat tata letak vertikal.
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Spacer buat ngasi jarak.
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Edit Username",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )

                    // Spacer lagi buat ngasi jarak.
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = updateProfile,
                        onValueChange = { updateProfile = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 16.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                            .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium),
                        label = { Text("Masukan Username baru") },
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text
                        ),
                        singleLine = false
                    )
                    Button(
                        onClick = { updateUsername(updateProfile, context) },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Save")
                    }
                }
            }
        }

        // Row buat tombol navigasi back.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    navController.navigate("AkunProfil")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Back")
            }
        }
    }
}

// Fungsi buat update username di Firebase.
fun updateUsername(newUsername: String, context: Context) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    val profileUpdates = UserProfileChangeRequest.Builder()
        .setDisplayName(newUsername)
        .build()

    user?.updateProfile(profileUpdates)
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Berhasil Upload Username, Silahkan klik back ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Gagal mengupdate username", Toast.LENGTH_SHORT).show()
            }
        }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun UpdateProfilePreview() {
    val navController = rememberNavController()
    MaterialTheme {
        UpdateProfile(navController = navController)
    }
}
