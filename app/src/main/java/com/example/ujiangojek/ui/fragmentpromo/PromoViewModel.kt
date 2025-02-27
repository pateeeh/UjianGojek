package com.example.ujiangojek.ui.fragmentpromo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListPosterResponse
import com.example.ujiangojek.data.response.PosterItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoViewModel: ViewModel() {
    private val _listPoster =MutableLiveData<List<PosterItem>>()
    val listPoster: MutableLiveData<List<PosterItem>> = _listPoster

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findAllPoster()
    }

    private fun findAllPoster() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllPoster()
        client.enqueue(object : Callback<ListPosterResponse> {
            override fun onFailure(call: Call<ListPosterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(
                call: Call<ListPosterResponse>,
                response: Response<ListPosterResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful){
                    _listPoster.value = response.body()?.data
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
        })
    }
}