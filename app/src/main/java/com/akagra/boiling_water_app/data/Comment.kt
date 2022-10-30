package com.akagra.boiling_water_app.data

data class Comment(
    val movieId: Int? = 0,
    val imageURL: String? = "",
    val displayName: String? = "",
    val commentText: String? = "",
    val createdAt : Long= 0
)