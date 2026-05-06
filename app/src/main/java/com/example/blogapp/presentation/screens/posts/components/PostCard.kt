package com.example.blogapp.presentation.screens.posts.components

import com.example.blogapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.blogapp.domain.model.Post
import com.example.blogapp.presentation.navigation.DetailsScreen
import com.example.blogapp.presentation.screens.posts.PostsViewModel

@Composable
fun PostCard(
    post: Post,
    navController: NavHostController,
    viewModel: PostsViewModel = hiltViewModel()
){
    Card(
        modifier = Modifier
            .padding(top = 15.dp)
            .clickable{
                navController.navigate(route = DetailsScreen.PostDetail.passPost(post.toJson()))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = post.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = post.user?.username ?: "",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = 5.dp)
            ) {
                if(post.likes.contains(viewModel.currentUser?.uid)){
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable{
                                viewModel.deleteLikePost(post.id, viewModel.currentUser?.uid ?:"")
                            },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = ""
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable{
                                viewModel.likePost(post.id, viewModel.currentUser?.uid ?:"")
                            },
                        painter = painterResource(id = R.drawable.like_outline),
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = post.likes.size.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
    }
}