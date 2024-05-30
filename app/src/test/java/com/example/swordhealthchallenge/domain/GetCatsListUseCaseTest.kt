package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.data.remote.model.CatImageResponse
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.usecases.GetCatsListUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatsListUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = GetCatsListUseCase(repository, localRepository)

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

        val catListResponseMock = listOf(
            CatResponse(
                id = "CatId1",
                name = "Breed1",
                image = CatImageResponse(
                    url = "URL1",
                    id = "ImageId1",
                ),
                temperament = "Temperament1",
                origin = "Origin1",
                description = "Description1",
            ),
            CatResponse(
                id = "CatId2",
                name = "Breed2",
                image = CatImageResponse(
                    url = "URL2",
                    id = "ImageId2",
                ),
                temperament = "Temperament2",
                origin = "Origin2",
                description = "Description2",
            ),
        )

        every { repository.getCatList() } returns Single.just(catListResponseMock)
        every { localRepository.saveCats(any()) } returns Completable.complete()

        useCase()
            .test()
            .assertResult(catDomainModelListMocks)
    }



}