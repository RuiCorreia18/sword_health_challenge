package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFavouriteCatsUseCase @Inject constructor(
    private val repository: CatRepository,
    private val localRepository: CatLocalRepository
) {

    operator fun invoke(): Single<List<FavouriteInfoDomainModel>> {
        return repository.getFavouriteCats()
            .flatMap { favCatList ->
                // Create a listOf Observables for setFavouriteCat
                val observables = favCatList.map { cat ->
                    localRepository.setFavouriteCat(cat.imageId, cat.favouriteId)
                        // Emits FavouriteInfoDomainModel as response for request
                        .toSingleDefault(cat)
                }

                // Waits for all requests to be done
                Observable.fromIterable(observables)
                    // Collects all emitted cat from toSingleDefault
                    .flatMapSingle { it }
                    // Creates a list and emits a Single response
                    .toList()
            }
    }

    fun getCatByImageId(imageId: String): Single<FavouriteCatDomainModel> =
        repository.getCatByImageId(imageId)
}