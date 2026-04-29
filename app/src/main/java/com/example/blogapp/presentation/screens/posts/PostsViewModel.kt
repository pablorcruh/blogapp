package com.example.blogapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.model.Response.*
import com.example.blogapp.domain.usecases.posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase
): ViewModel(){
    var postResponse by mutableStateOf<Response<List<Post>>?>(null)

    init{
        getPosts()
    }


    fun getPosts() = viewModelScope.launch {
        postResponse = Loading
        postsUseCase.invoke().collect() { result ->
            postResponse = result
        }
    }
}