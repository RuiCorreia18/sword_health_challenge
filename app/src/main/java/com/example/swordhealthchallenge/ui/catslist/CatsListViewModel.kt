package com.example.swordhealthchallenge.ui.catslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Cat
import javax.inject.Inject

class CatsListViewModel @Inject constructor() : ViewModel() {
    private val _catsList = MutableLiveData<List<Cat>>()
    val catsList: LiveData<List<Cat>>
            get() = _catsList
}