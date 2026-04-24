package com.example.blogapp.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blogapp.domain.model.Response
import com.example.blogapp.presentation.screens.login.components.ProgressBar
import com.example.blogapp.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()) {

    when(val response = viewModel.createPostResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current, "Publicacion creada correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}