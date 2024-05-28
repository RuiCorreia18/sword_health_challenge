package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import javax.inject.Inject

class DeleteFavouriteUseCase @Inject constructor(
    private val repository: CatRepository
) {
    fun deleteFavouriteCat(favouriteId: String) = repository.deleteFavouriteCat(favouriteId)
}