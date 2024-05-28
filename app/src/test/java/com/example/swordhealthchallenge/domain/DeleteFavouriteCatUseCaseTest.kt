package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import org.junit.Test

class DeleteFavouriteCatUseCaseTest {

    private val repository: CatRepository = mockk()
    private val useCase = DeleteFavouriteCatUseCase(repository)

    @Test
    fun `when delete is success should complete`(){
        every { repository.deleteFavouriteCat(any()) } returns Completable.complete()

        useCase.deleteFavouriteCat("")
            .test()
            .await()
            .assertComplete()

        verify(exactly = 1) { repository.deleteFavouriteCat(any()) }
    }
}