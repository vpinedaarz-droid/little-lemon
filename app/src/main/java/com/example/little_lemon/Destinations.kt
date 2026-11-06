package com.example.little_lemon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route: String
    val icon: ImageVector
    val title: String
}

object Onboarding: Destinations {
    override val route = "Onboarding"
    override val icon = Icons.Filled.Handshake
    override val title = "Onboarding"
}

object Home: Destinations {
    override val route = "Home"
    override val icon = Icons.Filled.Home
    override val title = "Home"
}

object Profile: Destinations {
    override val route = "Profile"
    override val icon = Icons.Filled.AccountCircle
    override val title = "Profile"
}