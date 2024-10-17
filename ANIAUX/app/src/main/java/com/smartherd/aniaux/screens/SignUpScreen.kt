package com.smartherd.aniaux.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.smartherd.aniaux.AuthViewModel.AuthViewModel
import com.smartherd.aniaux.R
import com.smartherd.aniaux.components.CustomCheckbox
import com.smartherd.aniaux.components.DynamicSelectTextField
import com.smartherd.aniaux.components.HeadingTextComponents
import com.smartherd.aniaux.components.MyTextFiedComponent
import com.smartherd.aniaux.components.PasswordTextField

import com.smartherd.aniaux.ui.theme.ANIAUXTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var isAgreeChecked by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("Select Role") }
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.login_image),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxWidth()
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(12.dp))
            HeadingTextComponents(value = "Sign UP")
            Spacer(modifier = Modifier.height(10.dp))

            MyTextFiedComponent(
                labelValue = "Email Address",
                icon = Icons.Default.Email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(10.dp))

            MyTextFiedComponent(
                labelValue = "Full Name",
                icon = Icons.Default.AccountCircle,
                onValueChange = { fullName = it }
            )
            Spacer(modifier = Modifier.height(10.dp))

            PasswordTextField(
                text = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(10.dp))

            PasswordTextField(
                text = "Confirm Password",
                onValueChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(20.dp))
            DynamicSelectTextField(
                selectedValue = selectedRole,
                options = listOf("Buyer", "Seller"),
                label = "Role",
                onValueChangedEvent = { newRole -> selectedRole = newRole }
            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomCheckbox(
                isChecked = isAgreeChecked,
                onCheckedChange = { isAgreeChecked = it },
                label = "I agree to the terms and conditions"
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = errorMessage, color = Color.Red)

            FilledTonalButton(
                onClick = {
                    if (password == confirmPassword && isAgreeChecked) {
                        authViewModel.registerUser(
                            email,
                            password,
                            fullName,
                            selectedRole,
                            onSuccess = {
                                navController.navigate("BottomAppBar")
                            },
                            onFailure = { error ->
                                errorMessage = error
                            }
                        )
                    } else {
                        errorMessage = "Passwords do not match or Terms not agreed"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                enabled = isAgreeChecked
            ) {
                Text(text = "Sign Up", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Already have an account? Login",
                color = Color.Blue,
                modifier = Modifier
                    .clickable(onClick = { navController.navigate("Login") })
                    .padding(top = 8.dp, bottom = 30.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    text: String,
    onValueChange: (String) -> Unit, // Add this line to accept a callback
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    androidx.compose.material3.TextField(
        value = password,
        onValueChange = {
            password = it
            onValueChange(it) // Call the passed callback when value changes
        },
        label = { Text(text) },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFiedComponent(
    labelValue: String,
    icon: ImageVector,
    onValueChange: (String) -> Unit, // Accepts a lambda for value change
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") } // State to hold text input
    androidx.compose.material3.TextField(
        value = text,
        onValueChange = { newText ->
            text = newText // Update the local state
            onValueChange(newText) // Propagate the change to the parent
        },
        label = { Text(text = labelValue) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun Icon(imageVector: Any, contentDescription: Nothing?) {

}


