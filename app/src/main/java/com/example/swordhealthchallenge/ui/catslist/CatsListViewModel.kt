package com.example.swordhealthchallenge.ui.catslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase
) : ViewModel() {
    private val _catsList = MutableLiveData<List<Cat>>(emptyList())
    val catsList: LiveData<List<Cat>>
        get() = _catsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getCatList() {
        getCatListUseCase.getCatList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { catList ->
                    //TODO Added this in case i got time for pagination
                    val newCatList = catsList.value!!.plus(catList)
                    _catsList.value = newCatList
                },
                onError = {
                    Log.e("ERROR CAT API BREED", it.toString())
                    _errorMessage.value = "Problem getting cats list"
                }
            )
            .addTo(compositeDisposable)
    }

    fun searchCats(catSearch: String) {
        getCatListUseCase.searchCat(catSearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _catsList.value = it },
                onError = {
                    Log.e("ERROR CAT API BREED", it.toString())
                    _errorMessage.value = "Problem searching for Cats"
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
                    val tempCatList = catsList.value!!
                    tempCatList.find { cat -> cat.imageId == imageId }?.favouriteId = it
                    _catsList.value = tempCatList
                },
                onError = {
                    Log.e("ERROR FAVOURITE CAT", it.toString())
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
                    val tempCatList = catsList.value!!
                    tempCatList.find { cat -> cat.favouriteId == favouriteId }?.favouriteId = ""
                    _catsList.value = tempCatList
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