package com.example.swordhealthchallenge.ui.catslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import com.example.swordhealthchallenge.ui.utils.CatDomainModelFakes.catFav
import com.example.swordhealthchallenge.ui.utils.CatDomainModelFakes.catNoFav
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CatListViewModelTest {
    private val getCatListUseCase: GetCatListUseCase = mockk()
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase = mockk()
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase = mockk()
    private val ioSchedulers: Scheduler = Schedulers.trampoline()
    private val mainSchedulers: Scheduler = Schedulers.trampoline()
    private val viewModel = CatsListViewModel(
        getCatListUseCase,
        postFavouriteCatUseCase,
        deleteFavouriteCatUseCase,
        ioSchedulers,
        mainSchedulers
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `test getCatList success`() {
        val expected = listOf(catNoFav)

        every { getCatListUseCase.getCatList() } returns Single.just(expected)

        viewModel.getCatList()

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `test searchCats success`() {
        val search = "bree"

        val expected = listOf(catNoFav)

        every { getCatListUseCase.searchCat(search)} returns Single.just(expected)

        viewModel.searchCats(search)

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `test favouriteCat success`() {
        val imageId = "ImageId1"
        val favId = "FavId1"
        val cats = listOf(catNoFav)
        val expected = listOf(catFav)

        every { postFavouriteCatUseCase.postFavouriteCat(imageId)} returns Single.just(favId)
        addCatsToLiveData(cats)

        viewModel.favouriteCat(imageId)

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `test deleteFavouriteCat success`() {
        val favId = "FavId1"
        val cats = listOf(catFav)
        val expected = listOf(catNoFav)

        every { deleteFavouriteCatUseCase.deleteFavouriteCat(favId)} returns Completable.complete()
        addCatsToLiveData(cats)

        viewModel.deleteFavouriteCat(favId)

        assertEquals(expected, viewModel.catsList.value)
    }

    private fun addCatsToLiveData(catsList: List<Cat>){
        every { getCatListUseCase.getCatList()} returns Single.just(catsList)
        viewModel.getCatList()
    }
}


