package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatListUseCase @Inject constructor(
    private val repository: CatRepository
) {
    fun getCatList(): Single<List<Cat>> = repository.getCatList()
    fun getCatImage(imageId: String): Single<String> = repository.getCatImage(imageId)
}