package com.example.blogapp.domain.usecases.auth

import com.example.blogapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase@Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.currentUser
}