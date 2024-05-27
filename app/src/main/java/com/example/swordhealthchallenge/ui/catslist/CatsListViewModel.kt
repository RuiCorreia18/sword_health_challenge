package com.example.swordhealthchallenge.ui.catslist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : ViewModel() {
    private val _catsList = MutableLiveData<List<Cat>>(emptyList())
    val catsList: LiveData<List<Cat>>
        get() = _catsList

    private val compositeDisposable by lazy { CompositeDisposable() }

    @SuppressLint("CheckResult")
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
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}