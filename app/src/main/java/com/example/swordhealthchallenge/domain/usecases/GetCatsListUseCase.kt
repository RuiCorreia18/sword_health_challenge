package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.NetworkUtils
import com.example.swordhealthchallenge.data.toCatDomainModelList
import com.example.swordhealthchallenge.data.toDomainList
import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatsListUseCase @Inject constructor(
    private val repository: CatRepository,
    private val localRepository: CatLocalRepository,
    private val networkUtils: NetworkUtils,
) {
    operator fun invoke(): Single<List<CatDomainModel>> {
        return if (networkUtils.isInternetAvailable()) {
            repository.getCatList()
                .flatMap { cats ->
                    localRepository.saveCats(cats)
                        .andThen(Single.just(cats.toDomainList()))
                }
        } else {
            localRepository.getAllCats().flatMap { Single.just(it.toCatDomainModelList()) }
        }
    }

}