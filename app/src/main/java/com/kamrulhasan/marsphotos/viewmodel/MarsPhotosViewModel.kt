package com.kamrulhasan.marsphotos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamrulhasan.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

private const val TAG = "MarsPhotosViewModel"

class MarsPhotosViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status : LiveData<String> = _status

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
                Log.d(TAG, "getMarsPhotos:  ${listResult.size}")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d(TAG, "getMarsPhotos: Internet is not connected.")
            }
        }
    }
//    public fun getMarsPhotosSize() : String{
//        return status.toString()
//    }
}