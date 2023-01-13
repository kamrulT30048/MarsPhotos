package com.kamrulhasan.marsphotos.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamrulhasan.marsphotos.model.MarsPhotos
import com.kamrulhasan.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

private const val TAG = "MarsPhotosViewModel"

class MarsPhotosViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private var _photos = MutableLiveData<List<MarsPhotos>>()
    val photos: LiveData<List<MarsPhotos>> = _photos

    init {
        _status.value = "Data Loading...."
//        if(verifyAvailableNetwork())
//        getMarsPhotos()
    }

    fun hitServer(){
        getMarsPhotos()
    }

    fun InternetNotConnected(){
        _status.value = "Please Check Your Internet Connection"
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
//                _status.value = "Success: ${listResult.size} Mars photos retrieved"
                _photos.value = listResult
                Log.d(TAG, "getMarsPhotos:  ${listResult.size}")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d(TAG, "getMarsPhotos: Internet is not connected.")
            }
        }
    }

}

//    fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
//        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return  networkInfo != null && networkInfo.isConnected
//    }

//    public fun getMarsPhotosSize() : String{
//        return status.toString()
//    }
//}