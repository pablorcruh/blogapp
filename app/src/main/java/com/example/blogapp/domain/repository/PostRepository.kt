package com.example.blogapp.domain.repository

import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    fun getPosts(): Flow<Response<List<Post>>>

    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
    suspend fun create(post: Post, file: File): Response<Boolean>
    suspend fun delete(idPost: String): Response<Boolean>
}