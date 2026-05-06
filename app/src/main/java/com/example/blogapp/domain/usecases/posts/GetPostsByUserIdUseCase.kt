package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsByUserIdUseCase @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(userId: String) = repository.getPostsByUserId(userId)
}