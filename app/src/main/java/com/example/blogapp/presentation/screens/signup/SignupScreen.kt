package com.example.blogapp.presentation.screens.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.blogapp.presentation.components.DefaultTopBar
import com.example.blogapp.presentation.screens.signup.components.Signup
import com.example.blogapp.presentation.screens.signup.components.SignupContent
import com.example.blogapp.presentation.ui.theme.BlogAppTheme

@Composable
fun SignupScreen(navController: NavHostController){
    Scaffold (
        topBar = { DefaultTopBar(
            "Nuevo Usuario",
            true,
            navController) },
        content = {
            SignupContent(it, navController)
        },
        bottomBar = {}
    )
    Signup(navController)
}

@Preview(showBackground = true, showSystemUi =true)
@Composable
fun PreviewSignup(){
    BlogAppTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignupScreen(rememberNavController())
        }
    }
}