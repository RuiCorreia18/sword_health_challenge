package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.domain.Model.Cat

fun List<CatResponse>.toDomainList(): List<Cat> {
    return this.map {
        Cat(
            id = it.id,
            breed = it.name,
            imageUrl = it.reference_image_id ?: ""
        )
    }
}
