package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {

    operator fun invoke() = repository.getPosts()
}