package com.example.blogapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import com.example.blogapp.domain.model.Response.*
import com.example.blogapp.domain.usecases.auth.AuthUseCases
import com.example.blogapp.domain.usecases.posts.GetPostsUseCase
import com.example.blogapp.domain.usecases.posts.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val postsUseCase: PostsUseCase
): ViewModel(){
    var postResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)
    var deleteLikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser = authUseCases.getCurrentUser()

    init{
        getPosts()
    }

    fun likePost(idPost: String, idUser: String) = viewModelScope.launch {
        likeResponse = Loading
        val result = postsUseCase.likePostUseCase.invoke(idPost, idUser)
        likeResponse = result
    }

    fun deleteLikePost(idPost: String, idUser: String) = viewModelScope.launch {
        deleteLikeResponse = Loading
        val result = postsUseCase.deleteLikePostUseCase.invoke(idPost, idUser)
        deleteLikeResponse = result
    }

    fun getPosts() = viewModelScope.launch {
        postResponse = Loading
        postsUseCase.getPosts.invoke().collect() { result ->
            postResponse = result
        }
    }
}