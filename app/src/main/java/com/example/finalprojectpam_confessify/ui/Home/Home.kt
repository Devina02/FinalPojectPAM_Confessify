package com.example.finalprojectpam_confessify.ui.Home

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

data class NavItemState(
    val title: String,           // Data untuk menyimpan judul item navigasi
    val selectedIcon: ImageVector,  // Ikon yang ditampilkan saat item terpilih
    val unselectedIcon: ImageVector  // Ikon yang ditampilkan saat item tidak terpilih
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,  // Digunakan untuk mengontrol navigasi antar layar
    modifier: Modifier = Modifier      // Mengubah tata letak atau gaya tampilan
) {
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)  // Menyimpan indeks item navigasi terpilih
    }

    val items = listOf(
        NavItemState(
            title = "Create",
            selectedIcon = Icons.Filled.Add,       // Ikon saat item terpilih
            unselectedIcon = Icons.Outlined.Add    // Ikon saat item tidak terpilih
        ),
        NavItemState(
            title = "Home",
            selectedIcon = Icons.Filled.Home,       // Ikon saat item terpilih
            unselectedIcon = Icons.Outlined.Home    // Ikon saat item tidak terpilih
        ),
        NavItemState(
            title = "Account",
            selectedIcon = Icons.Filled.Face,       // Ikon saat item terpilih
            unselectedIcon = Icons.Outlined.Face    // Ikon saat item tidak terpilih
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier.fillMaxWidth(),  // buat ngatur tata letak isi top app bar
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "Tersembunyi dalam suara, Terungkap di Confesisfy!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                },
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),  // arak dan buat bikin kayak silinder gitu
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF8692F7)  // Warna background top app bar
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),  // jarak dan buat bikin kayak silinder gitu
                containerColor = Color(0xFF8692F7)  // Warna background bottom navigation bar
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavState == index,
                        onClick = { bottomNavState = index },  // Menangani perubahan item terpilih pada bottom navigation
                        icon = {
                            Icon(
                                imageVector = if (bottomNavState == index) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        // Warna untuk item terpilih dan indikator pada bottom navigation
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF552A27),
                            selectedTextColor = Color(0xFF63332F),
                            indicatorColor = Color(0xFFBB7E7A)
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(){
                items(count = 500){
                    Text(
                        text = "Confession: $it",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth().padding(20.dp)
                        )
                }
            }
        }
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())  // Gantilah dengan instance NavHostController yang sesuai
}
