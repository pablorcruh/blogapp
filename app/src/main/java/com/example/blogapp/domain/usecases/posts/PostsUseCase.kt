package com.example.blogapp.domain.usecases.posts

data class PostsUseCase (
    val createPost: CreatePostUseCase,

    val getPosts: GetPostsUseCase
)