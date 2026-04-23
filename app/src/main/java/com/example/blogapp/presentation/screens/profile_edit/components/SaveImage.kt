package com.example.blogapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blogapp.domain.model.Response
import com.example.blogapp.presentation.screens.login.components.ProgressBar
import com.example.blogapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.google.rpc.context.AttributeContext.Resource

@Composable
fun SaveImage(
     viewModel: ProfileEditViewModel = hiltViewModel()
){
    when(val respose = viewModel.saveImageResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            viewModel.onUpdate(respose.data)
        }

        is Response.Failure ->{
            Toast.makeText(LocalContext.current, respose.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}