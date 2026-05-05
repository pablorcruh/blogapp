package com.example.blogapp.presentation.screens.detail_post

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.screens.detail_post.components.DetailPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPostScreen(navController: NavHostController){
    Scaffold(
        topBar = {},
        content = { DetailPostContent(navController) },
        bottomBar = {}
    )
}