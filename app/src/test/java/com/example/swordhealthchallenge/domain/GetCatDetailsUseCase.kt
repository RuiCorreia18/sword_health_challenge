package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.Model.CatDetails
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatDetailsUseCase {

    private val repository: CatRepository = mockk()
    private val useCase = GetCatDetailsUseCase(repository)

    @Test
    fun `when getCatDetails is success should return CatDetails`() {
        val catId = "CatId1"
        val catDetailsMock = CatDetails(
            id = catId,
            breed = "Breed1",
            imageUrl = "URL1",
            imageId = "ImageId1",
            favouriteId = "FavId1",
            origin = "Origin",
            temperament = "Temperament",
            description = "Descrition",
        )

        every { repository.getCatDetails(catId) } returns Single.just(catDetailsMock)

        useCase.getCatDetails(catId)
            .test()
            .await()
            .assertComplete()
            .values()
            .contains(catDetailsMock)

        verify(exactly = 1) { repository.getCatDetails(catId) }
    }
}