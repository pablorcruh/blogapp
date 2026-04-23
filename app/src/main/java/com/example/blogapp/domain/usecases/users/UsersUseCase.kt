package com.example.blogapp.domain.usecases.users

data class UsersUseCase(
    val createUser: CreateUserUseCase,
    val getUserById: GetUserByIdUseCase,
    val updateUser: UpdateUserUseCase,
    val saveImage: SaveImageUseCase

)
