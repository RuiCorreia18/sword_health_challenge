package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.usecases.SearchCatsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class SearchCatsUseCaseTest {

    private val repository: CatRepository = mockk()
    private val useCase = SearchCatsUseCase(repository)

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

        useCase(searchText)
            .test()
            .assertResult(catDomainModelListMocks)
    }
}