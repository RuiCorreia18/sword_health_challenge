package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatDetailsUseCase @Inject constructor(
    private val repository: CatRepository
) {
    operator fun invoke(catId: String): Single<CatDetailsDomainModel> = repository.getCatDetails(catId)
}
