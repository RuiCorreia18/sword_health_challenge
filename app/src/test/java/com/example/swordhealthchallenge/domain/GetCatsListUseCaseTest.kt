package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.NetworkUtils
import com.example.swordhealthchallenge.domain.usecases.GetCatsListUseCase
import com.example.swordhealthchallenge.utils.CatDomainModelFakes.catDomainModelNoFav
import com.example.swordhealthchallenge.utils.CatDomainModelFakes.catDomainModelNoFav2
import com.example.swordhealthchallenge.utils.CatResponseFakes.catResponseMock1
import com.example.swordhealthchallenge.utils.CatResponseFakes.catResponseMock2
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatsListUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val networkUtils: NetworkUtils = mockk()
    private val useCase = GetCatsListUseCase(repository, localRepository, networkUtils)

    @Test
    fun `when getCatList is success should return list of Cat`() {
        val catDomainModelListMocks = listOf(catDomainModelNoFav, catDomainModelNoFav2)

        val catListResponseMock = listOf(catResponseMock1, catResponseMock2)

        every { repository.getCatList() } returns Single.just(catListResponseMock)
        every { localRepository.saveCats(any()) } returns Completable.complete()
        every { networkUtils.isInternetAvailable() } returns true

        useCase()
            .test()
            .assertResult(catDomainModelListMocks)
    }


}