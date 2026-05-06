package com.example.blogapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blogapp.domain.model.Response
import com.example.blogapp.presentation.screens.login.components.ProgressBar
import com.example.blogapp.presentation.screens.posts.PostsViewModel

@Composable
fun DeleteLikePost(viewModel: PostsViewModel = hiltViewModel()){
    when(val response = viewModel.deleteLikeResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, response.exception?.message ?: "error desconocido", Toast.LENGTH_LONG).show()
        }
        is Response.Success -> {

        }
        else -> {}
    }
}