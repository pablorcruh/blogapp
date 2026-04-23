package com.example.blogapp.presentation.screens.profile_edit

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.blogapp.domain.model.User
import com.example.blogapp.presentation.components.DefaultTopBar
import com.example.blogapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.example.blogapp.presentation.screens.profile_edit.components.SaveImage
import com.example.blogapp.presentation.screens.profile_edit.components.Update

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditSreen(
    navController: NavHostController
){

    Scaffold (
        topBar = { DefaultTopBar(
            "Editar Usuario",
            true,
            navController) },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    SaveImage()
    Update()
}
