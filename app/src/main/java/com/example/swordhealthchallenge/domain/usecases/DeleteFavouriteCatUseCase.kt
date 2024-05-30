package com.example.swordhealthchallenge.domain.usecases

import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteFavouriteCatUseCase @Inject constructor(
    private val repository: CatRepository,
    private val localRepository: CatLocalRepository
) {
    operator fun invoke(favouriteId: String): Completable {
        return repository.deleteFavouriteCat(favouriteId).andThen(
            localRepository.deleteFavouriteCat(favouriteId)
        )

    }
}
