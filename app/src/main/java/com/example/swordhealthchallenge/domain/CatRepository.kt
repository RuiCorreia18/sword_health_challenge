package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getCatList(): Single<List<Cat>>
    fun getCatImage(imageId: String): Single<String>
}