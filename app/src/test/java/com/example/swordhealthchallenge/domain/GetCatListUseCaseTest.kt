package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
import com.example.swordhealthchallenge.domain.Model.FavouriteInfo
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatListUseCaseTest {

    private val repository: CatRepository = mockk()
    private val useCase = GetCatListUseCase(repository)

    @Test
    fun `when getCatList is success should return list of Cat`() {
        val catListMock = listOf(
            Cat(
                id = "CatId1",
                breed = "Breed1",
                imageUrl = "URL1",
                imageId = "ImageId1",
                favouriteId = ""
            ),
            Cat(
                id = "CatId2",
                breed = "Breed2",
                imageUrl = "URL2",
                imageId = "ImageId2",
                favouriteId = ""
            ),
        )

        every { repository.getCatList() } returns Single.just(catListMock)

        useCase.getCatList()
            .test()
            .assertComplete()
            .assertNoErrors()
            .values()
            .contains(catListMock)
    }

    @Test
    fun `when searchCat is success should return list of Cat`() {
        val searchText = "breed"
        val catListMock = listOf(
            Cat(
                id = "CatId1",
                breed = "Breed1",
                imageUrl = "URL1",
                imageId = "ImageId1",
                favouriteId = ""
            ),
            Cat(
                id = "CatId2",
                breed = "Breed2",
                imageUrl = "URL2",
                imageId = "ImageId2",
                favouriteId = ""
            ),
        )

        every { repository.searchCat(searchText) } returns Single.just(catListMock)

        useCase.searchCat(searchText)
            .test()
            .assertComplete()
            .assertNoErrors()
            .values()
            .contains(catListMock)
    }

    @Test
    fun `when getFavouriteCats is success should return list of FavouriteInfo`() {
        val catFavInfoListMock = listOf(
            FavouriteInfo(
                imageId = "ImageId1",
                favouriteId = "FavId1",
            ),
            FavouriteInfo(
                imageId = "ImageId2",
                favouriteId = "FavId2",
            ),
        )

        every { repository.getFavouriteCats() } returns Single.just(catFavInfoListMock)

        useCase.getFavouriteCats()
            .test()
            .assertComplete()
            .assertNoErrors()
            .values()
            .contains(catFavInfoListMock)
    }

    @Test
    fun `when getCatByImageId is success should return list of FavouriteCat`() {
        val imageId = "ImageId1"
        val catFavListMock = listOf(
            FavouriteCat(
                id = "CatId1",
                breed = "Breed1",
                imageUrl = "URL1",
                imageId = "ImageId1",
                favouriteId = "FavId1",
                lifeSpan= "LifeSpan",
            ),
            FavouriteCat(
                id = "CatId2",
                breed = "Breed2",
                imageUrl = "URL2",
                imageId = "ImageId2",
                favouriteId = "FavId2",
                lifeSpan= "LifeSpan",
            ),
        )

        val favCatMock = FavouriteCat(
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
            .assertComplete()
            .assertNoErrors()
            .values()
            .contains(favCatMock)
    }
}