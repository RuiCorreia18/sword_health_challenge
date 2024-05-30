package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import org.junit.Test

class DeleteFavouriteCatUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = DeleteFavouriteCatUseCase(repository, localRepository)

    @Test
    fun `when delete is success should complete`() {
        every { repository.deleteFavouriteCat(any()) } returns Completable.complete()
        every { localRepository.deleteFavouriteCat(any()) } returns Completable.complete()

        useCase("")
            .test()
            .assertComplete()
    }
}