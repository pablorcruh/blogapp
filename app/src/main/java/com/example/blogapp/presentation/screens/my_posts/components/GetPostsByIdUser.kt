package com.example.blogapp.presentation.screens.my_posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blogapp.domain.model.Response
import com.example.blogapp.presentation.screens.login.components.ProgressBar
import com.example.blogapp.presentation.screens.my_posts.MyPostsViewModel

@Composable
fun GetPostsByIdUser(
    navController: NavHostController,
    viewModel: MyPostsViewModel = hiltViewModel()
){
    when(val response = viewModel.postsResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success ->{
            MyPostsContent(navController, response.data)
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, response.exception?.message ?: "error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}