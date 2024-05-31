package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.SearchCatsUseCase
import com.example.swordhealthchallenge.utils.CatDomainModelFakes.catDomainModelNoFav
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
        val catDomainModelListMocks = listOf(catDomainModelNoFav)

        every { repository.searchCat(searchText) } returns Single.just(catDomainModelListMocks)

        useCase(searchText)
            .test()
            .assertResult(catDomainModelListMocks)
    }
}