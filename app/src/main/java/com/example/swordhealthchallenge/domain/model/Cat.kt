package com.example.swordhealthchallenge.domain.model

data class Cat(
    val id: String,
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    var favouriteId: String = ""
)
