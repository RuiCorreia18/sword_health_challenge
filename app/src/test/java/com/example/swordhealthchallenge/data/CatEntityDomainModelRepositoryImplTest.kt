package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.Breed
import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatDetailsResponse
import com.example.swordhealthchallenge.data.entities.CatImageResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import com.example.swordhealthchallenge.data.entities.PostFavouriteResponse
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class CatEntityDomainModelRepositoryImplTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()
    private val repository = CatRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `should return list of Cat if getCatList is success`() {
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

        val catListResponseMock = listOf(
            CatResponse(
                id = "CatId1",
                name = "Breed1",
                image = CatImageResponse(
                    url = "URL1",
                    id = "ImageId1",
                ),
            ),
            CatResponse(
                id = "CatId2",
                name = "Breed2",
                image = CatImageResponse(
                    url = "URL2",
                    id = "ImageId2",
                ),
            ),
        )

        every { remoteDataSource.getCatList() } returns Single.just(catListResponseMock)

        repository.getCatList()
            .test()
            .assertResult(catDomainModelListMocks)
    }

    @Test
    fun `should return list of Cat if searchCat is success`() {
        val searchText = "bree"
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

        val catListResponseMock = listOf(
            CatResponse(
                id = "CatId1",
                name = "Breed1",
                image = CatImageResponse(
                    url = "URL1",
                    id = "ImageId1",
                ),
            ),
            CatResponse(
                id = "CatId2",
                name = "Breed2",
                image = CatImageResponse(
                    url = "URL2",
                    id = "ImageId2",
                ),
            ),
        )

        every { remoteDataSource.searchCat(searchText) } returns Single.just(catListResponseMock)

        repository.searchCat(searchText)
            .test()
            .assertResult(catDomainModelListMocks)
    }

    @Test
    fun `should return favourite id if postFavouriteCat is success`() {
        val imageUrl = "ImageUrl"
        val favBody = FavouriteCatBody(imageUrl)
        val favId = "FavId"
        val postFavResponse = PostFavouriteResponse(favId)

        every { remoteDataSource.postFavouriteCat(favBody) } returns Single.just(postFavResponse)

        repository.postFavouriteCat(imageUrl)
            .test()
            .assertResult(favId)
    }

    @Test
    fun `should return list of favourite info if getFavouriteCats is success`() {
        val favCatResponseListMock = listOf(
            FavouriteCatResponse(
                id = "1",
                image_id = "ImageId1"
            ),
            FavouriteCatResponse(
                id = "2",
                image_id = "ImageId2"
            ),
        )

        val favInfoListMock = listOf(
            FavouriteInfoDomainModel(
                favouriteId = "1",
                imageId = "ImageId1"
            ),
            FavouriteInfoDomainModel(
                favouriteId = "2",
                imageId = "ImageId2"
            ),
        )

        every { remoteDataSource.getFavouriteCats() } returns Single.just(favCatResponseListMock)

        repository.getFavouriteCats()
            .test()
            .assertResult(favInfoListMock)
    }

    @Test
    fun `should return favourite cat if getCatImage is success`() {
        val imageId = "ImageId"
        val catByImageResponseListMock = CatByImageResponse(
            breeds = listOf(
                Breed(
                    id = "1",
                    life_span = "5 - 8",
                    name = "Breed1"
                )
            ),
            imageId = imageId,
            url = "ImageUrl"
        )

        val favCatMock = FavouriteCatDomainModel(
            id = "1",
            favouriteId = "",
            breed = "Breed1",
            imageId = imageId,
            imageUrl = "ImageUrl",
            lifeSpan = "5"
        )

        every { remoteDataSource.getCatImage(imageId) } returns Single.just(catByImageResponseListMock)

        repository.getCatByImageId(imageId)
            .test()
            .assertResult(favCatMock)
    }

    @Test
    fun `should return cat details if getCatDetails is success`() {
        val catId = "1"
        val catDetailsResponseMock = CatDetailsResponse(
            id = "1",
            breed = "Breed1",
            temperament = "Temperament",
            origin = "Origin",
            description = "Description",
            imageId = "ImageId1"
        )

        val catDetailsDomainModelMock = CatDetailsDomainModel(
            id = catId,
            breed = "Breed1",
            imageId = "ImageId1",
            origin = "Origin",
            temperament = "Temperament",
            description = "Description",
        )

        every { remoteDataSource.getCatDetails(catId) } returns Single.just(catDetailsResponseMock)

        repository.getCatDetails(catId)
            .test()
            .assertResult(catDetailsDomainModelMock)
    }

    @Test
    fun `should delete fav if deleteFavouriteCat is completed`() {
        val favId = "1"

        every { remoteDataSource.deleteFavouriteCat(favId) } returns Completable.complete()

        repository.deleteFavouriteCat(favId)
            .test()
            .assertComplete()
    }
}
