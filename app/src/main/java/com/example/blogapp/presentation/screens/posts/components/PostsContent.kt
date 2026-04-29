package com.example.blogapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response

@Composable
fun PostsContent(
    posts: List<Post>
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(posts.size) {item ->
            Text(
                text  = "Item #$item",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

        }
    }
}