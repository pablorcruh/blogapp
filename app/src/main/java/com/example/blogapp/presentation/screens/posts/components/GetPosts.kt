package com.example.blogapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.domain.model.Response
import com.example.blogapp.presentation.screens.login.components.ProgressBar
import com.example.blogapp.presentation.screens.posts.PostsViewModel

@Composable
fun GetPosts(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){

    when(val response = viewModel.postResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            PostsContent(navController, posts = response.data)
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, response.exception?.message ?: "error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}