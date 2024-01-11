package com.example.finalprojectpam_confessify.ui.Home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    firestore: FirebaseFirestore
) {
    var confessions by remember { mutableStateOf<List<String>>(emptyList()) }

    // Fetch data from Firestore
    LaunchedEffect(firestore) {
        val confessionsCollection = firestore.collection("confessions")
        val snapshot = confessionsCollection.get().await()
        confessions = snapshot.documents.mapNotNull { it.getString("confessText") ?: "" }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Box untuk nagtur si tata letak isi dari top app bar.
                    Box(
                        modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
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

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(vertical = 4.dp)
                ) {
                    items(confessions) { confession ->
                        Isi(
                            Confess = "Confession: $confession",
                            onDeleteClick = {
                                deleteConfession(confession, firestore)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun Isi(Confess: String, onDeleteClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Confess: ")
                Text(text = Confess)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ElevatedButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(if (expanded) "Show less" else "Show more")
                }

                // Tombol "Delete".
                IconButton(
                    onClick = {
                        onDeleteClick.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

private fun deleteConfession(confession: String, firestore: FirebaseFirestore) {
    firestore.collection("confessions")
        .whereEqualTo("confessText", confession)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                document.reference.delete()
            }
        }
        .addOnFailureListener { e ->
            println("Error deleting document: $e")
        }
}