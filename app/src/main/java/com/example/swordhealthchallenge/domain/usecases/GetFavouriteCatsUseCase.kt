package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFavouriteCatsUseCase @Inject constructor(
    private val repository: CatRepository
) {

    operator fun invoke(): Single<List<FavouriteInfoDomainModel>> {
        return repository.getFavouriteCats()
    }
}