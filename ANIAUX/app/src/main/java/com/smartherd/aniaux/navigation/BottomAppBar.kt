package com.smartherd.aniaux.navigation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smartherd.aniaux.AuthViewModel.AuthViewModel
import com.smartherd.aniaux.R
import com.smartherd.aniaux.screens.Categories
import com.smartherd.aniaux.screens.Favourite
import com.smartherd.aniaux.screens.Home
import com.smartherd.aniaux.screens.Login
import com.smartherd.aniaux.screens.Profile
import com.smartherd.aniaux.screens.Screens
import com.smartherd.aniaux.screens.SignUpScreen
import com.smartherd.aniaux.ui.theme.grey

@Composable
fun MybottomAppBar(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    var isClicked by remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(Icons.Default.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = grey) {
                // Home Button
                BottomNavItem(
                    icon = Icons.Default.Home,
                    description = "Home",
                    isSelected = selected.value == Icons.Default.Home,
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Screens.Screens.Home.screen) {
                            popUpTo(0)
                        }
                    }
                )

                // Categories Button
                BottomNavItem(
                    painter = painterResource(id = R.drawable.sort_icon),
                    description = "Categories",
                    isSelected = selected.value == Icons.Default.ShoppingCart,
                    onClick = {
                        selected.value = Icons.Default.ShoppingCart
                        navController.navigate(Screens.Screens.Categories.screen) {
                            popUpTo(0)
                        }
                    }
                )

                // Floating Action Button
                Box(
                    modifier = Modifier
                        .weight(2.5f)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(onClick = {
                        Toast.makeText(context, "Floating action clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = grey)
                    }
                }

                // Favorite Button
                BottomNavItem(
                    icon = Icons.Default.Favorite,
                    description = "Favorite",
                    isSelected = selected.value == Icons.Default.Favorite,
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navController.navigate(Screens.Screens.Favourite.screen) {
                            popUpTo(0)
                        }
                    }
                )

                // Profile Button (Navigates to ProfileScreen)
                BottomNavItem(
                    icon = Icons.Default.AccountCircle,
                    description = "Profile",
                    isSelected = selected.value == Icons.Default.AccountCircle,
                    onClick = {
                        selected.value = Icons.Default.AccountCircle
                        navController.navigate(Screens.Screens.Profile.screen) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Screens.Home.screen) { Home() }
            composable(Screens.Screens.Favourite.screen) { Favourite() }
            composable(Screens.Screens.Categories.screen) { Categories() }
            composable(Screens.Screens.Profile.screen) { Profile() }  // ProfileScreen linked here
        }
    }
}

fun composable(screen: String, function: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

fun NavHost(
    navController: NavController,
    startDestination: String,
    modifier: Modifier,
    function: () -> Unit
) {



}

@Composable
fun BottomNavItem(
    icon: ImageVector? = null,
    painter: Painter? = null,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) = IconButton(onClick = onClick, modifier = Modifier.javaClass.componentType.name.lastIndex.Column {
    weight(1.5f.toInt())
}) {
    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Color.Black else MaterialTheme.colorScheme.primary
        )
    }
}

private fun Int.Column(function: () -> FontVariation.Setting): Modifier {
    TODO("Not yet implemented")
}






