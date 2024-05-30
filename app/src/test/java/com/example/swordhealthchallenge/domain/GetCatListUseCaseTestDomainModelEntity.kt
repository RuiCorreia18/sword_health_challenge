package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatListUseCaseTestDomainModelEntity {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = GetCatListUseCase(repository, localRepository)

    @Test
    fun `when getCatList is success should return list of Cat`() {
        val catDomainModelListMocks = listOf(
            CatDomainModel(
                id = "CatId1",
                breed = "Breed1",
                imageUrl = "URL1",
                imageId = "ImageId1",
                favouriteId = ""
            ),
            CatDomainModel(
                id = "CatId2",
                breed = "Breed2",
                imageUrl = "URL2",
                imageId = "ImageId2",
                favouriteId = ""
            ),
        )

        every { repository.getCatList() } returns Single.just(catDomainModelListMocks)

        useCase.getCatList()
            .test()
            .assertResult(catDomainModelListMocks)
    }

    @Test
    fun `when searchCat is success should return list of Cat`() {
        val searchText = "breed"
        val catDomainModelListMocks = listOf(
            CatDomainModel(
                id = "CatId1",
                breed = "Breed1",
                imageUrl = "URL1",
                imageId = "ImageId1",
                favouriteId = ""
            ),
            CatDomainModel(
                id = "CatId2",
                breed = "Breed2",
                imageUrl = "URL2",
                imageId = "ImageId2",
                favouriteId = ""
            ),
        )

        every { repository.searchCat(searchText) } returns Single.just(catDomainModelListMocks)

        useCase.searchCat(searchText)
            .test()
            .assertResult(catDomainModelListMocks)
    }

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

        useCase.getFavouriteCats()
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
            lifeSpan= "LifeSpan",
        )


        every { repository.getCatByImageId(imageId) } returns Single.just(favCatMock)

        useCase.getCatByImageId(imageId)
            .test()
            .assertResult(favCatMock)
    }
}