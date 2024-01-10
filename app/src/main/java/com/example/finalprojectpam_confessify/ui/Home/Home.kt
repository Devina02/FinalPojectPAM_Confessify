
package com.example.finalprojectpam_confessify.ui.Home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,  // Digunakan untuk mengontrol navigasi antar layar
    modifier: Modifier = Modifier      // Mengubah tata letak atau gaya tampilan
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Box untuk nagtur si tata letak isi dari top app bar.
                    Box(modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center) {
                        // Text judul aplikasi.
                        Text(
                            text = "CONFESSIFY!!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },//buat modif si appbar
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF8692F7)
                )
            )
        },
        content = { contentPadding ->
            Column(
                modifier = modifier
                    .padding(contentPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            // Handle button click for "Create"
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

                    Button(
                        onClick = {
                            // Handle button click for "Home"
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

                    Button(
                        onClick = { navController.navigate("AkunProfil")}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Profile"
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Profile")
                    }
                }

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(vertical = 4.dp)
                ) {
                    items(count = 500) {
                        Isi(Confess = "Confession: $it")
                    }
                }
            }
        }
    )
}
@Composable
private fun Isi (Confess: String) {
    // Mutable state untuk menyimpan status "expanded" dari isi.
    var expanded by remember { mutableStateOf(false) }

    // Animasi untuk mengatur ekstra padding saat si isi di-expanded.
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // Surface sebagai container untuk isi.
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth() // Memastikan lebar penuh
    ) {
        // Row untuk menampilkan teks Confession dan tombol "Show more/less".
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Column untuk menampilkan teks Confession.
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Confess: ")
                Text(text = Confess)
            }
            // Tombol untuk mengganti status "expanded".
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreenPreview() {

    val navController = rememberNavController()

    HomeScreen(navController = navController)
}

