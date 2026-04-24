package com.example.blogapp.domain.model

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String= "",
    var idUser: String = "",
    var image: String = ""
)
