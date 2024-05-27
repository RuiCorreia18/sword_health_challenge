package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.Model.CatDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatDetailsUseCase @Inject constructor(
    private val repository: CatRepository
) {
    fun getCatDetails(catId: String): Single<CatDetails> = repository.getCatDetails(catId)
}