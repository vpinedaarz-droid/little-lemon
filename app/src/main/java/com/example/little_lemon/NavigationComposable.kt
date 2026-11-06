package com.example.little_lemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    val usuarioNuevo: Boolean = false
    val destination: Destinations
    if (usuarioNuevo) {
        destination = Onboarding
    } else {
        destination = Home
    }

    NavHost(
        navController = navController,
        startDestination = destination.route)
    {
        composable(Home.route) { Home( navController) }
        composable(Onboarding.route) { Onboarding(navController)}
        composable(Profile.route) { Profile(navController)}
    }
}