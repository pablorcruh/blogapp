package com.example.blogapp.domain.usecases.auth

import com.example.blogapp.domain.model.User
import com.example.blogapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(user: User) = authRepository.signUp(user)
}