package com.example.blogapp.domain.usecases.users

import com.example.blogapp.domain.model.User
import com.example.blogapp.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(user: User) = repository.createUser(user)
}