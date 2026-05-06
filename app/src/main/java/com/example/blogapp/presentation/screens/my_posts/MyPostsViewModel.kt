package com.example.blogapp.presentation.screens.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.model.Response.Loading
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.posts.GetPostsByUserIdUseCase
import com.example.blogapp.domain.usecases.posts.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUsecases: PostsUseCase,
    private val authUseCase: AuthUseCases
): ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    val currentUser = authUseCase.getCurrentUser()

    init{
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        postsResponse = Loading
        postsUsecases.getPostByUserId(currentUser?.uid ?: "").collect { result ->
            postsResponse = result
        }
    }

    fun deletePost(idPost: String) = viewModelScope.launch {
        deleteResponse = Loading
        val result = postsUsecases.deletePostUseCase.invoke(idPost)
        deleteResponse = result
    }
}