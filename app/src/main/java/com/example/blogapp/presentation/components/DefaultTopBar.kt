package com.example.blogapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.ui.theme.Red500
import com.example.blogapp.presentation.ui.theme.Red700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navController: NavHostController? = null,

){
    TopAppBar(
        title = {
            Text(
                title,
                fontSize = 19.sp
            )
        },
        Modifier.background(Red500),
        navigationIcon = {
            if(upAvailable){
                IconButton(onClick = {navController?.popBackStack()}){
                   Icon(
                       imageVector = Icons.Default.ArrowBack,
                       contentDescription = "",
                       tint = Color.White
                   )
                }
            }
        }
    )
}