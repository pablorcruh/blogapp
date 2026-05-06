package com.example.blogapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.screens.posts.components.DeleteLikePost
import com.example.blogapp.presentation.screens.posts.components.GetPosts
import com.example.blogapp.presentation.screens.posts.components.LikePost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){
    Scaffold(
        topBar = {},
        content = {
            GetPosts(navController)
        },
        bottomBar = {}
     )
    LikePost()
    DeleteLikePost()
}