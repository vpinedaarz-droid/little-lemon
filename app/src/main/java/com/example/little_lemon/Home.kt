package com.example.little_lemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.ui.theme.LittleLemonColor
import com.example.little_lemon.ui.theme.Typography
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController : NavHostController,
         database: AppDatabase) {

    var filtro by remember { mutableStateOf("") }
    var tipoPlato by remember { mutableStateOf("")}
    val databaseMenuItems by database.menuItemDao().getAll()
        .observeAsState(emptyList<MenuItemRoom>())

    var menuItems : List<MenuItemRoom> = databaseMenuItems

    if (tipoPlato != "") {
        menuItems =databaseMenuItems.filter { it.category == tipoPlato }
        if (filtro.length > 0 ) {
            menuItems = menuItems.filter { it.title.contains(filtro) }
        }
    } else {
        if (filtro.length> 0) {
            menuItems = databaseMenuItems.filter { it.title.contains(filtro) }
        }
    }


    Column() {
        Row (modifier=Modifier.fillMaxWidth().padding(top=20.dp),
            horizontalArrangement = Arrangement.spacedBy(
                10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.littlelemonimgtxt),
                contentScale = ContentScale.Fit,
                contentDescription = "logotipo little lemon",
                modifier = Modifier.weight(0.7f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            IconButton( onClick ={
                navController.navigate(Profile.route)
            },
                modifier = Modifier.weight(0.3f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp)))
            {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = "Icono perfil"
                )
            }
        }
        Box(modifier = Modifier.background(color = LittleLemonColor.green)
            .height(280.dp).padding(start = 10.dp, end = 10.dp)) {
            Column() {
                Text("Little Lemon",
                    fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = LittleLemonColor.yellow,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding( start = 5.dp, end = 20.dp).
                    fillMaxWidth())
                Column() {
                    Text("Chicago",
                        fontSize =20.sp, fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        style = Typography.titleLarge,
                        modifier = Modifier.padding( start = 5.dp, end = 20.dp).
                        fillMaxWidth()
                        )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text="We are family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            fontSize =15.sp, fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Left,
                            color = Color.White,
                            style = Typography.bodyMedium,
                            modifier = Modifier.padding( start = 5.dp, top = 20.dp)
                                .weight(0.6f)
                            )
                        Image(
                            painter = painterResource(id = R.drawable.heroimage),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Hero Image",
                            modifier = Modifier.weight(0.4f)
                                .size(100.dp)
                        )
                    }
                    TextField(
                        textStyle = Typography.bodyMedium,
                        value = filtro,
                        modifier=Modifier.padding(top = 25.dp),
                        onValueChange = { newFiltro -> filtro = newFiltro },
                        //label = { Text("Name") },
                        singleLine = true,
                        leadingIcon = {
                            Icon( imageVector = Icons.Default.Search,
                            contentDescription = "Buscar")
                        },
                        placeholder = { Text("Enter search phrases") },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedTextColor = Color.Gray,
                            focusedContainerColor = LittleLemonColor.cloud,
                            unfocusedContainerColor = LittleLemonColor.cloud
                        )
                    )
                }
            }
        }
        Text("ORDER FOR DELIVERY",
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = LittleLemonColor.charcoal,
            style = Typography.headlineLarge,
            modifier = Modifier.padding( start = 20.dp, top = 20.dp,
                            bottom = 10.dp)
            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LitBoton("Starters",
                onClick = { tipoPlato = "starters"
                    menuItems = databaseMenuItems.filter { it.category == tipoPlato }
                })
            LitBoton("Mains",
                onClick = { tipoPlato = "mains"
                    menuItems = databaseMenuItems.filter { it.category == tipoPlato }
                })
            LitBoton("Deserts",
                onClick = { tipoPlato = "desserts"
                    menuItems = databaseMenuItems.filter { it.category == tipoPlato }
                })
            LitBoton( text = "Drinks",
                onClick = { tipoPlato = "drinks"
                    menuItems = databaseMenuItems.filter { it.category == tipoPlato }
                })
        }
        MenuItems( menuItems)
    }

}

@Composable
fun LitBoton(
    text:String,
    onClick: () -> Unit
) {
Button(
    onClick = onClick,
    shape = RoundedCornerShape(size = 8.dp),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = LittleLemonColor.charcoal
        )
) {
    Text(text= text,
        fontSize = 15.sp,
        color = LittleLemonColor.charcoal)
}

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                ) {
                    Column(modifier = Modifier.weight(0.8f)) {
                        Text(menuItem.title,
                            color = LittleLemonColor.charcoal,
                            style = Typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                            )
                        Text(menuItem.description,
                            color = LittleLemonColor.charcoal,
                            style = Typography.bodySmall)
                        Text(
                            text = "%.2f".format(menuItem.price),
                            color = LittleLemonColor.charcoal,
                            style = Typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    GlideImage(
                      model = menuItem.image,
                        contentDescription = "Imagen comida",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.weight(0.2f)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navFake = rememberNavController()
    val context = LocalContext.current
    val fakeData = remember {
        Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }


    Home(navFake, fakeData)
}

