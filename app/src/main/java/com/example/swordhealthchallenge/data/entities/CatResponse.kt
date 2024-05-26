package com.example.swordhealthchallenge.data.entities

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("reference_image_id")
    val reference_image_id: String,
)
