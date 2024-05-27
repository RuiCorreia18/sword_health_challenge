package com.example.swordhealthchallenge.domain.Model

data class Cat(
    val id: String,
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    var favourite: Boolean = false
)