package com.example.blogapp.domain.repository

import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.model.Response
import java.io.File

interface PostRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>
}