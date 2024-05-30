package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostFavouriteCatUseCase @Inject constructor(
    private val repository: CatRepository,
    private val localRepository: CatLocalRepository
) {
    fun postFavouriteCat(imageId: String): Single<String> {
        return repository.postFavouriteCat(imageId)
            .flatMap { favId ->
                localRepository.setFavouriteCat(imageId, favId)
                    .andThen(Single.just(favId))
            }
    }
}
