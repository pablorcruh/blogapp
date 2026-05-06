package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(private val repository: PostRepository) {
    suspend fun invoke(post: Post,file: File? ) = repository.update(post, file)
}