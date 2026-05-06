package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.repository.PostRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend fun invoke(postId: String) = repository.delete(postId)
}