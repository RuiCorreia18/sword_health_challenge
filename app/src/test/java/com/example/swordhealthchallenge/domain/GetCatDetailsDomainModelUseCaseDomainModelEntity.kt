package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatDetailsDomainModelUseCaseDomainModelEntity {

    private val repository: CatRepository = mockk()
    private val useCase = GetCatDetailsUseCase(repository)

    @Test
    fun `when getCatDetails is success should return CatDetails`() {
        val catId = "CatId1"
        val catDetailsDomainModelMock = CatDetailsDomainModel(
            id = catId,
            breed = "Breed1",
            imageUrl = "URL1",
            imageId = "ImageId1",
            favouriteId = "FavId1",
            origin = "Origin",
            temperament = "Temperament",
            description = "Descrition",
        )

        every { repository.getCatDetails(catId) } returns Single.just(catDetailsDomainModelMock)

        useCase.getCatDetails(catId)
            .test()
            .assertResult(catDetailsDomainModelMock)
    }
}