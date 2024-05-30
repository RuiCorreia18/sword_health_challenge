package com.example.swordhealthchallenge.ui.catsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatsListUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.SearchCatsUseCase
import com.example.swordhealthchallenge.ui.utils.CatDomainModelFakes.catDomainModelFav
import com.example.swordhealthchallenge.ui.utils.CatDomainModelFakes.catDomainModelNoFav
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CatsListViewModelTest {
    private val getCatListUseCase: GetCatsListUseCase = mockk()
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase = mockk()
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase = mockk()
    private val searchCatsUseCase: SearchCatsUseCase = mockk()
    private val ioSchedulers: Scheduler = Schedulers.trampoline()
    private val mainSchedulers: Scheduler = Schedulers.trampoline()
    private val viewModel = CatsListViewModel(
        getCatListUseCase,
        postFavouriteCatUseCase,
        deleteFavouriteCatUseCase,
        searchCatsUseCase,
        ioSchedulers,
        mainSchedulers
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when getCatList success should fill livedata`() {
        val expected = listOf(catDomainModelNoFav)

        every { getCatListUseCase() } returns Single.just(expected)

        viewModel.getCatList()

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `when searchCats success should fill livedata`() {
        val search = "bree"

        val expected = listOf(catDomainModelNoFav)

        every { searchCatsUseCase(search) } returns Single.just(expected)

        viewModel.searchCats(search)

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `when favouriteCat success should update livedata`() {
        val imageId = "ImageId1"
        val favId = "FavId1"
        val cats = listOf(catDomainModelNoFav)
        val catDomainModelFav = CatDomainModel(
            id = "CatId1",
            breed = "Breed1",
            imageUrl = "URL1",
            imageId = "ImageId1",
            favouriteId = "FavId1"
        )
        val expected = listOf(catDomainModelFav)

        every { postFavouriteCatUseCase(imageId) } returns Single.just(favId)
        addCatsToLiveData(cats)

        viewModel.favouriteCat(imageId)

        assertEquals(expected, viewModel.catsList.value)
    }

    @Test
    fun `when deleteFavouriteCat success should update livedata`() {
        val favId = "FavId1"
        val cats = listOf(catDomainModelFav)
        val expected = listOf(catDomainModelNoFav)

        every { deleteFavouriteCatUseCase(favId) } returns Completable.complete()
        addCatsToLiveData(cats)

        viewModel.deleteFavouriteCat(favId)

        assertEquals(expected, viewModel.catsList.value)
    }

    private fun addCatsToLiveData(catsList: List<CatDomainModel>) {
        every { getCatListUseCase() } returns Single.just(catsList)
        viewModel.getCatList()
    }
}
