package com.example.blogapp.domain.usecases.users

import com.example.blogapp.domain.model.User
import com.example.blogapp.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(user: User) = repository.update(user)
}