package com.example.swordhealthchallenge.ui.catslist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : ViewModel() {
    private val _catsList = MutableLiveData<List<Cat>>()
    val catsList: LiveData<List<Cat>>
            get() = _catsList


    @SuppressLint("CheckResult")
    fun getCatList(){
        getCatListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {

                    _catsList.value = it
                }
            )
    }
}