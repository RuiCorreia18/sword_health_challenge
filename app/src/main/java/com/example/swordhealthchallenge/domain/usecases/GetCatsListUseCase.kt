package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.data.toDomainList
import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDomainModel
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
                    .andThen(Single.just(cats.toDomainList()))
            }
    }
}
