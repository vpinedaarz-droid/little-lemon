package com.example.little_lemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MenuNetworkdata (
    val menu: List<MenuItemNetwork>
)

@Serializable
class MenuItemNetwork(
    @SerialName( "id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
) {
    fun toMenuItemRoom() : MenuItemRoom {
        return MenuItemRoom(
            id, title, description, price,
            image, category
        )
    }
}