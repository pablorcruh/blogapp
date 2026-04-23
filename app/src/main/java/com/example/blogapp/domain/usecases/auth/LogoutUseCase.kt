package com.example.blogapp.domain.usecases.auth

import com.example.blogapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.logout()
}