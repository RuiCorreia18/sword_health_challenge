package com.example.swordhealthchallenge.data.entities

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: CatImageResponse,
)

data class CatImageResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)

data class FavouriteCat(
    val image_id: String
)