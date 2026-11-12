package com.example.little_lemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController,
               database: AppDatabase) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(
            "LittleLemonPrefs",
            Context.MODE_PRIVATE
        )
    }

    val usuarioRegistrado = sharedPreferences.getBoolean("registrado", false)
    val destination: Destinations
    if (!usuarioRegistrado) {
        destination = Onboarding
    } else {
        destination = Home
    }

    NavHost(
        navController = navController,
        startDestination = destination.route)
    {
        composable(Home.route) { Home( navController, database) }
        composable(Onboarding.route) { Onboarding(navController)}
        composable(Profile.route) { Profile(navController)}
    }
}