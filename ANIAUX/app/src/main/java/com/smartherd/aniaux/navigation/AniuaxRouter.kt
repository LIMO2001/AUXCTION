package com.smartherd.aniaux.navigation

import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.smartherd.aniaux.AuthViewModel.AuthViewModel
import com.smartherd.aniaux.screens.Login
import com.smartherd.aniaux.screens.Screens
import com.smartherd.aniaux.screens.SignUpScreen

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "signUp"
    ) {
        composable("signUp") {
            SignUpScreen(modifier, navController, authViewModel)
        }
        composable("login") {
            Login(modifier, navController, authViewModel)
        }
        composable("bottomAppBar") {
            MybottomAppBar(modifier, navController, authViewModel)
        }
        composable("admin") {
            admin(modifier, navController, authViewModel) // Replace with your AdminScreen implementation
        }
    }
}

private fun Any.admin(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    TODO("Not yet implemented")
}
