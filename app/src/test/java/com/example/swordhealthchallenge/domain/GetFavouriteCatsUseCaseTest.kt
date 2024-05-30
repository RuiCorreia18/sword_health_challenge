package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import com.example.swordhealthchallenge.domain.usecases.GetFavouriteCatsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetFavouriteCatsUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = GetFavouriteCatsUseCase(repository, localRepository)

    @Test
    fun `when getFavouriteCats is success should return list of FavouriteInfo`() {
        val catFavInfoListMock = listOf(
            FavouriteInfoDomainModel(
                imageId = "ImageId1",
                favouriteId = "FavId1",
            ),
            FavouriteInfoDomainModel(
                imageId = "ImageId2",
                favouriteId = "FavId2",
            ),
        )

        every { repository.getFavouriteCats() } returns Single.just(catFavInfoListMock)
        every { localRepository.setFavouriteCat(any(), any()) } returns Completable.complete()

        useCase()
            .test()
            .assertResult(catFavInfoListMock)
    }

    @Test
    fun `when getCatByImageId is success should return list of FavouriteCat`() {
        val imageId = "ImageId1"

        val favCatMock = FavouriteCatDomainModel(
            id = "CatId1",
            breed = "Breed1",
            imageUrl = "URL1",
            imageId = "ImageId1",
            favouriteId = "FavId1",
            lifeSpan = "LifeSpan",
        )


        every { repository.getCatByImageId(imageId) } returns Single.just(favCatMock)

        useCase.getCatByImageId(imageId)
            .test()
            .assertResult(favCatMock)
    }
}