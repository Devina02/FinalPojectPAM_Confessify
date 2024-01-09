package com.example.finalprojectpam_confessify.ui.Home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Data class yang merepresentasikan state setiap item di bottom navigation bar.
data class NavItemState(
    val title: String,             // Judul item navigasi
    val selectedIcon: ImageVector, // Ikon saat item terpilih
    val unselectedIcon: ImageVector // Ikon saat item tidak terpilih
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,  // Digunakan untuk mengontrol navigasi antar layar
    modifier: Modifier = Modifier      // Mengubah tata letak atau gaya tampilan
) {
    // Mutable state untuk menyimpan indeks item navigasi terpilih pada bottom navigation.
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }

    // Daftar item di bottom navigation bar.
    val items = listOf(
        NavItemState(
            title = "Create",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add
        ),
        NavItemState(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavItemState(
            title = "Account",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face
        )
    )

    // Scaffold merupakan struktur dasar dari layar yang memiliki top bar, bottom bar, dan content.
    Scaffold(
        topBar = {
            // untuk nampilin judul aplikasi.
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
        bottomBar = {
            // NavigationBar untuk nampilin bottom navigation bar.
            NavigationBar(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                containerColor = Color(0xFF8692F7)
            ) {
                // Membuat setiap item di bottom navigation bar.
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavState == index,
                        onClick = { bottomNavState = index },
                        icon = {
                            // ini buat bikin icon sesuai dengan icon yang terpilih atau tidak terpilih.
                            Icon(
                                imageVector = if (bottomNavState == index) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        // Menentukan warna untuk item terpilih.
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF552A27),
                            selectedTextColor = Color(0xFF63332F),
                            indicatorColor = Color(0xFF673AB7)
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        // Column buat content pada layar.
        Column(
            modifier = modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // LazyColumn untuk menampilkan daftar Confession yang dapat di-scroll.
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp)
            ) {
                items(count = 500) {
                    // Menampilkan isi dengan Confession sesuai indeks.
                    Isi (Confess = "Confession: $it")
                }
            }
        }
    }
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

