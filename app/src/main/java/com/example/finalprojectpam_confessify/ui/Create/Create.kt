package com.example.finalprojectpam_confessify.ui.Create

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpam_confessify.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.compose.ui.text.style.TextAlign


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateScreen(navController: NavHostController) {

    // MutableState untuk menyimpan nilai dari TextField.
    var confessText by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Tampilan utama menggunakan Box sebagai kontainer paling luar.
    Box(
        modifier = Modifier
            .fillMaxSize(),
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
                .fillMaxWidth().align(Alignment.Center)
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
                    // Spacer untuk memberikan jarak.
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Buat Confess Mu",
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

                    // Spacer lagi untuk memberikan jarak.
                    Spacer(modifier = Modifier.height(16.dp))

                    // TextField untuk memasukkan teks confess.
                    TextField(
                        value = confessText,
                        onValueChange = { confessText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 16.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                            .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium),
                        label = { Text("Confess Text") },
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text
                        ),
                        singleLine = false
                    )

                    // Tombol untuk mengunggah teks confess ke Firebase Firestore.
                    Button(
                        onClick = {
                            val firestore = Firebase.firestore
                            val confessData = hashMapOf(
                                "confessText" to confessText,
                            )
                            firestore.collection("confessions")
                                .add(confessData)
                                .addOnSuccessListener { documentReference ->
                                    println("DocumentSnapshot added with ID: ${documentReference.id}")

                                    // Menampilkan toast keberhasilan.
                                    Toast.makeText(context, "Berhasil Upload Confess, Silahkan ke page Home", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    println("Error adding document: $e")
                                }
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Upload")
                    }
                }
            }
        }

        // Row untuk menampilkan tombol navigasi.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Tombol Create
            Button(
                onClick = {
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

            // Tombol Home
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

            // Tombol Profile
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun CreateScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        CreateScreen(navController = navController)
    }
}
