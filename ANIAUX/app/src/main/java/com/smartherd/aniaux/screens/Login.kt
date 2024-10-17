package com.smartherd.aniaux.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.smartherd.aniaux.AuthViewModel.AuthViewModel
//


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.smartherd.aniaux.R
import com.smartherd.aniaux.components.CustomCheckbox
import com.smartherd.aniaux.components.DynamicSelectTextField
import com.smartherd.aniaux.components.HeadingTextComponents
import com.smartherd.aniaux.components.MyTextFiedComponent
import com.smartherd.aniaux.components.PasswordTextField

import com.smartherd.aniaux.ui.theme.ANIAUXTheme



@Composable
fun Login(modifier: Modifier, navController: NavController , authViewModel: AuthViewModel){
        var isAgreeChecked by remember { mutableStateOf(false) }
        var selectedRole by remember { mutableStateOf("Select Role") }
        var password by remember { mutableStateOf("") }
         var email by remember { mutableStateOf("") }
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ){
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(R.drawable.login_image),
                    contentDescription ="null",
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .size(150.dp)
                        .fillMaxWidth()
                        .clip(shape = CircleShape) // Optional: add rounded corners
                )
                Spacer(modifier = Modifier.height(12.dp))
                HeadingTextComponents(value = "Sign In")
                Spacer(modifier = Modifier.height(10.dp))
                MyTextFiedComponent(labelValue = "Email Address", icon = Icons.Default.Email, onValueChange ={email=it} )
                Spacer(modifier = Modifier.height(10.dp))

                // "Forgot Password?"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End // Align to the right
                ) {
                    Text(
                        text = "Forgot Password?",
                        modifier = Modifier.clickable {
                            // Action for forgot password click
                            navController.navigate("forgotPassword")
                        },
                        color = Color.Blue
                    )
                }

                PasswordTextField(text = "Password", onValueChange = {password=it})

                Spacer(modifier = Modifier.height(15.dp))
                //
                // Submit Button
                FilledTonalButton(
                    onClick = {
                        if(password!="" && email!="")
                        {
                            authViewModel.login(email,password,  onSuccess = {
                                navController.navigate("BottomAppBar")
                            },
                                onFailure = { error ->

                                })
                        }
                        navController.navigate("BottomAppBar")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color.Black, // Button background color
                        contentColor = Color.White    // Text color
                    ),

                    // enabled = isAgreeChecked // Enable button only if the checkbox is checked
                ) {
                    Text(text = "Submit")
                }


                Spacer(modifier = Modifier.height(10.dp))
                // Redirect to Login Text
                Text(
                    text = "have No account? SignUp",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable(onClick = {
                            navController.navigate("signUp")
                            //AniuaxRouter.navigateTo()
                        })
                        .padding(top = 8.dp)
                )

            }

        }



}
