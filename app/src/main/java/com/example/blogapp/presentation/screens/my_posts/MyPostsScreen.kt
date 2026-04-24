package com.example.blogapp.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.blogapp.presentation.navigation.DetailsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navController: NavHostController){
    Scaffold(
        topBar = {},
        content = {
            Text(text = "MyPostsScreen")
        },
        bottomBar = {},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 60.dp),
                onClick = {
                    navController.navigate(route = DetailsScreen.NewPost.route)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add post"
                )
            }
        }
    )
}