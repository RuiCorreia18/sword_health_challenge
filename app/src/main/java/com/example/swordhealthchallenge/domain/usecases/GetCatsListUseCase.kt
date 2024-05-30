package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatListUseCase @Inject constructor(
    private val repository: CatRepository,
    private val localRepository: CatLocalRepository
) {
    fun getCatList(): Single<List<CatDomainModel>> {
        return repository.getCatList()
            .flatMap { cats ->
                localRepository.saveCats(cats)
                    .andThen(Single.just(cats))
            }
    }

    fun searchCat(search: String): Single<List<CatDomainModel>> = repository.searchCat(search)
    fun getFavouriteCats(): Single<List<FavouriteInfoDomainModel>> = repository.getFavouriteCats()
    fun getCatByImageId(imageId: String): Single<FavouriteCatDomainModel> =
        repository.getCatByImageId(imageId)
}

