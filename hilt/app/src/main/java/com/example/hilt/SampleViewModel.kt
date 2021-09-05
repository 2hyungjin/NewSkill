package com.example.hilt

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SampleViewModel
@ViewModelInject constructor(val userRepository: UserRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()
    private var job: Job? = null
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun getUsers() {
        job = viewModelScope.launch {
            val response = userRepository.getUsers()
            if (response.isSuccessful) {
                _users.postValue(response.body())
                isLoading.value = false
            } else {
                onError(response.message())
            }
        }
    }

    private fun onError(message: String) {
        isLoading.value = false
        Log.d("error", message)
    }

    override fun onCleared() {
        job?.cancel()
    }

}