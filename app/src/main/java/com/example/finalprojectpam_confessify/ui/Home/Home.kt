package com.example.finalprojectpam_confessify.ui.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalprojectpam_confessify.R

data class NavItemState(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }

    val items = listOf(
        NavItemState(
            title = "Create",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
        ),
        NavItemState(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavItemState(
            title = "Account",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "Tersembunyi dalam suara, terungkap di Confesisfy!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                            )
                    }

                },
                modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF8692F7)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp)),
                containerColor = Color(0xFF8692F7)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavState == index,
                        onClick = { bottomNavState = index },
                        icon = {
                            Icon(
                                imageVector = if (bottomNavState == index) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
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
            Text(
                text = items[bottomNavState].title,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
