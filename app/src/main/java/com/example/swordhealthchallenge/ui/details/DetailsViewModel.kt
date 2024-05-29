package com.example.swordhealthchallenge.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.model.CatDetails
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class DetailsViewModel @Inject constructor(
    private val getCatDetailsUseCase: GetCatDetailsUseCase,
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase,
    @Named("io") private val ioSchedulers: Scheduler,
    @Named("main") private val mainSchedulers: Scheduler,
) : ViewModel() {

    private val _catDetails = MutableLiveData<CatDetails>()
    val catDetails: LiveData<CatDetails>
        get() = _catDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getCatDetails(catId: String, catImageUrl: String, catFavouriteId: String) {
        getCatDetailsUseCase.getCatDetails(catId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = {
                    _catDetails.value = it.copy(
                        imageUrl = catImageUrl,
                        favouriteId = catFavouriteId
                    )
                },
                onError = {
                    updateError("ERROR FAVOURITE CAT", it, "Problem getting catDetails")
                }
            )
            .addTo(compositeDisposable)
    }

    fun favouriteCat(imageId: String) {
        postFavouriteCatUseCase.postFavouriteCat(imageId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = {
                    updateCatDetailsFavouriteId(it)
                },
                onError = {
                    updateError("ERROR POST FAVOURITE CAT", it, "Problem saving favourite cat")
                }
            )
            .addTo(compositeDisposable)
    }

    fun deleteFavouriteCat(favouriteId: String) {
        deleteFavouriteCatUseCase.deleteFavouriteCat(favouriteId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onComplete = {
                    updateCatDetailsFavouriteId("")
                },
                onError = {
                    updateError("ERROR DELETE FAVOURITE CAT", it, "Problem deleting favourite cat")
                }
            )
            .addTo(compositeDisposable)
    }

    private fun updateCatDetailsFavouriteId(favId: String) {
        val cat = catDetails.value!!.copy(favouriteId = favId)
        _catDetails.value = cat
    }

    private fun updateError(tag: String, throwable: Throwable, errorMessage: String) {
        Log.e(tag, throwable.toString())
        _errorMessage.value = errorMessage
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
