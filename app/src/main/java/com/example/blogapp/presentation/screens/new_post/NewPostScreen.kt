package com.example.blogapp.presentation.screens.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.components.DefaultButton
import com.example.blogapp.presentation.components.DefaultTopBar
import com.example.blogapp.presentation.screens.new_post.components.NewPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            DefaultTopBar(
                "New Post",
                upAvailable = true,
                navController = navController
                )
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp),
                text = "PUBLICAR",
                onClick = {
                }
            )
        }
    )
}