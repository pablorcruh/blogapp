package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.model.Post
import com.example.blogapp.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(post: Post, file: File) = postRepository.create(post, file)
}