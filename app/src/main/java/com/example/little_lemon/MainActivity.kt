package com.example.little_lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.ktor.http.ContentType
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.ui.theme.LittlelemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(engineFactory = Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", contentSubtype = "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittlelemonTheme {
                val navController = rememberNavController()

                Navigation(navController, database)
            }
        }
        lifecycleScope.launch (Dispatchers.IO){
            if (database.menuItemDao().isEmpty()) {
                val menuNetwork = fetchMenu()
                runOnUiThread {
                    saveMenuToDatabase(menuNetwork)
                }
            }
         }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        var response: MenuNetworkdata = MenuNetworkdata(emptyList())
        try {
        response = httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkdata>() }
        catch(e: Exception) {
            println(e.message)
        }
        return response.menu
    }

    fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        try {
            database.menuItemDao().insertAll(menuItemsRoom)
        }
        catch (e: Exception) {
            println(e.message)
        }

    }
}

