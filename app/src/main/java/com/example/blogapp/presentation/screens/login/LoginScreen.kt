package com.example.blogapp.presentation.screens.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.blogapp.presentation.screens.login.components.Login
import com.example.blogapp.presentation.ui.theme.BlogAppTheme
import com.example.blogapp.presentation.screens.login.components.LoginBottomBar
import com.example.blogapp.presentation.screens.login.components.LoginContent

@Composable
fun LoginScreen(navController: NavHostController){

    Scaffold(
        topBar = {},
        content = { LoginContent(it, navController) },
        bottomBar = { LoginBottomBar(navController) }
    )

    // ,anejo estado peticion de login
    Login(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    BlogAppTheme {
        LoginScreen(rememberNavController())
    }
}