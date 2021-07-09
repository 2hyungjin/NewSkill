package com.example.databindingwithviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    fun setName(name: String) {
        this.name.value = name
    }

}