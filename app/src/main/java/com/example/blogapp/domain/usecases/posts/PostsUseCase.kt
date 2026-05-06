package com.example.blogapp.domain.usecases.posts

data class PostsUseCase (
    val createPost: CreatePostUseCase,
    val getPosts: GetPostsUseCase,
    val getPostByUserId: GetPostsByUserIdUseCase,
    val deletePostUseCase: DeletePostUseCase,
    val updatePostUseCase: UpdatePostUseCase,
    val likePostUseCase: LikePostUseCase,
    val deleteLikePostUseCase: DeleteLikeUseCase
)