package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchCatsUseCase @Inject constructor(
    private val repository: CatRepository,
) {
    operator fun invoke(search: String): Single<List<CatDomainModel>> = repository.searchCat(search)
}