package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.Cat
import com.example.swordhealthchallenge.domain.model.FavouriteCat
import com.example.swordhealthchallenge.domain.model.FavouriteInfo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatListUseCase @Inject constructor(
    private val repository: CatRepository
) {
    fun getCatList(): Single<List<Cat>> = repository.getCatList()
    fun searchCat(search: String): Single<List<Cat>> = repository.searchCat(search)
    fun getFavouriteCats(): Single<List<FavouriteInfo>> = repository.getFavouriteCats()
    fun getCatByImageId(imageId: String): Single<FavouriteCat> = repository.getCatByImageId(imageId)
}
