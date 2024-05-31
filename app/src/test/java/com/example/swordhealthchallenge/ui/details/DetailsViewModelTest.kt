package com.example.swordhealthchallenge.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import com.example.swordhealthchallenge.utils.CatDetailsDomainModelFakes.catDetailsDomainModelFav1
import com.example.swordhealthchallenge.utils.CatDetailsDomainModelFakes.catDetailsDomainModelNoFav1
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {
    private val getCatDetailsUseCase: GetCatDetailsUseCase = mockk()
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase = mockk()
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase = mockk()
    private val ioSchedulers: Scheduler = Schedulers.trampoline()
    private val mainSchedulers: Scheduler = Schedulers.trampoline()
    private val viewModel = DetailsViewModel(
        getCatDetailsUseCase,
        postFavouriteCatUseCase,
        deleteFavouriteCatUseCase,
        ioSchedulers,
        mainSchedulers
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when getCatDetails success should fill livedata`() {
        val expected = catDetailsDomainModelNoFav1

        every { getCatDetailsUseCase("1") } returns Single.just(expected)
        viewModel.getCatDetails("1", "URL1", "")

        Assert.assertEquals(expected, viewModel.catDetails.value)
    }

    @Test
    fun `when favouriteCat success should update livedata`() {
        val favId = "FavId1"
        val imageId = "ImageId1"
        val expected = catDetailsDomainModelFav1

        every { postFavouriteCatUseCase(imageId) } returns Single.just(favId)
        every { getCatDetailsUseCase("1") } returns Single.just(expected)
        viewModel.getCatDetails("1", "URL1", favId)

        viewModel.favouriteCat(imageId)

        Assert.assertEquals(expected, viewModel.catDetails.value)
    }

    @Test
    fun `when deleteFavouriteCat success should update livedata`() {
        val favId = "FavId1"
        val imageId = "ImageId1"
        val expected = catDetailsDomainModelNoFav1

        every { deleteFavouriteCatUseCase(imageId) } returns Completable.complete()
        every { getCatDetailsUseCase("1") } returns Single.just(expected)
        viewModel.getCatDetails("1", "URL1", favId)

        viewModel.deleteFavouriteCat(imageId)

        Assert.assertEquals(expected, viewModel.catDetails.value)
    }
}
