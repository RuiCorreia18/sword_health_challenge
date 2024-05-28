package com.example.swordhealthchallenge.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.CatDetails
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getCatDetailsUseCase: GetCatDetailsUseCase,
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _catDetails.value = it.copy(
                        imageUrl = catImageUrl,
                        favouriteId = catFavouriteId
                    )
                },
                onError = {
                    Log.e("ERROR FAVOURITE CAT", it.toString())
                    _errorMessage.value = "Problem getting catDetails"
                }
            )
            .addTo(compositeDisposable)
    }

    fun favouriteCat(imageId: String) {
        postFavouriteCatUseCase.postFavouriteCat(imageId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val cat = catDetails.value!!.copy(favouriteId = it)
                    _catDetails.value = cat
                },
                onError = {
                    Log.e("ERROR POST FAVOURITE CAT", it.toString())
                    _errorMessage.value = "Problem saving favourite cat"
                }
            )
            .addTo(compositeDisposable)
    }

    fun deleteFavouriteCat(favouriteId: String) {
        deleteFavouriteCatUseCase.deleteFavouriteCat(favouriteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    val cat = catDetails.value!!.copy(favouriteId = "")
                    _catDetails.value = cat
                },
                onError = {
                    Log.e("ERROR DELETE FAVOURITE CAT", it.toString())
                    _errorMessage.value = "Problem deleting favourite cat"
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}