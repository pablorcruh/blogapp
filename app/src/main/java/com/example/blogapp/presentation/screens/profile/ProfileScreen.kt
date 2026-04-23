package com.example.blogapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.components.DefaultTopBar
import com.example.blogapp.presentation.screens.profile.components.ProfileContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold (
        topBar = {
            DefaultTopBar(
                title= "Editar Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileContent(navController)
        },
        bottomBar = {}
    )
}