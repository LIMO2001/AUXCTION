package com.smartherd.aniaux.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.smartherd.aniaux.R
import com.smartherd.aniaux.ui.theme.grey

@Composable
fun ProfileScreen() {
    // Sample buyer details (these could come from a data model)
    val name = "John Doe"
    val email = "john.doe@example.com"
    val phone = "+254 712 345 678"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Profile", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Profile picture
        Image(
            painter = painterResource(id = R.drawable.sample_avatar),  // Replace with your default image
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Basic Details
        Text(text = "Name: $name", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: $email", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Phone: $phone", fontSize = 16.sp)

        // Add more fields if necessary, like address, purchase history, etc.
    }
}


@Composable
fun Profile(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Profile", fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}