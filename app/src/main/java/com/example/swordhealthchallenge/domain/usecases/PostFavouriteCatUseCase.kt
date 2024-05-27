package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import javax.inject.Inject

class PostFavouriteCatUseCase @Inject constructor(
    private val repository: CatRepository
) {
    fun postFavouriteCat(imageId: String) = repository.postFavouriteCat(imageId)
}