package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class PostFavouriteCatUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = PostFavouriteCatUseCase(repository, localRepository)

    @Test
    fun `when postFavouriteCat is success should return String`() {
        val imageId = "ImageId1"
        val favId = "Favid"

        every { repository.postFavouriteCat(imageId) } returns Single.just(favId)
        every { localRepository.setFavouriteCat(any(),any()) } returns Completable.complete()

        useCase(imageId)
            .test()
            .await()
            .assertResult(favId)
    }

    @Test
    fun `when postFavouriteCat error should return error`() {
        val error = Throwable()

        every { repository.postFavouriteCat(any()) } returns Single.error(error)

        useCase("")
            .test()
            .assertNotComplete()
            .assertError(error)
    }
}