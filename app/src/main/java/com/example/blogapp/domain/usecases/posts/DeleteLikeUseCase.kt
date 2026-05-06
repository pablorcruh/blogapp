package com.example.blogapp.domain.usecases.posts

import com.example.blogapp.domain.repository.PostRepository
import javax.inject.Inject

class DeleteLikeUseCase @Inject constructor(private val repository: PostRepository)  {
    suspend fun invoke(idPost: String, idUser: String) = repository.deleteLikes(idPost, idUser)
}