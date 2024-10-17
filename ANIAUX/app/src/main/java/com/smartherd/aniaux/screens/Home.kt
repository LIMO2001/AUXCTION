package com.smartherd.aniaux.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.draw.clip
import com.smartherd.aniaux.R  // Adjust the import path for your resources
data class GridItemsSingle(val resource:Int,val desc:String)
private infix fun String.onPlaceBidClick(any: Any) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar with resized icon
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text(text = "Search...") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(24.dp)  // Resizing the icon
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = Color.Gray
            )
        )

        // Categories section
        Text(text = "Categories", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryIcon(R.drawable.dairy, "DAIRY")
            CategoryIcon(R.drawable.beef, "BEEF")
            CategoryIcon(R.drawable.heiferr, "HEIFERS")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "POPULAR BREEDS", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

        val itemsList = listOf(
            GridItemsSingle(R.drawable.ic_ayshre, "Ayshre"),
            GridItemsSingle(R.drawable.ic_jersey, "Jersey"),
            GridItemsSingle(R.drawable.ic_fres, "Fresian"),
            GridItemsSingle(R.drawable.zebu, "Zebu"),
            GridItemsSingle(R.drawable.angus, "Angus"),
            GridItemsSingle(R.drawable.simental, "Simental")
        )

        // LazyVerticalGrid for displaying items in grid format
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Number of columns
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp) // Padding between items
        ) {
            items(itemsList) { imageRes ->
                GridItem(imageRes, price = "60000 KSH", onBidClick = {
                    // Handle bid click
                })
            }
        }
    }
}

@Composable
fun GridItem(gridItem: GridItemsSingle, price: String, onBidClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = gridItem.resource),
            contentDescription = gridItem.desc,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = gridItem.desc,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black
        )

        // Price
        Text(
            text = "Price: 40000 KSH",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bid Button
        Button(
            onClick = { onBidClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Place Bid")
        }
    }
}

@Composable
fun CategoryIcon(iconRes: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp),
            tint = Color.White
        )
        Text(text = label, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

