package com.example.blogapp.domain.usecases.auth

data class AuthUseCases (
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val logout: LogoutUseCase,
    val signup: SignupUseCase
)