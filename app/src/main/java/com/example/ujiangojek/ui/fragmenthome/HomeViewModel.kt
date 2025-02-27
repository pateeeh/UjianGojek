package com.example.ujiangojek.ui.fragmenthome

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListProdukResponse
import com.example.ujiangojek.data.response.ProdukItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val _listProduk = MutableLiveData<List<ProdukItem>>()
    val listProduk: MutableLiveData<List<ProdukItem>> = _listProduk

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    init {
        findAllProduk()
    }

    private fun findAllProduk() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllProduk()
        client.enqueue(object : Callback<ListProdukResponse> {
            override fun onFailure(call: Call<ListProdukResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(
                call: Call<ListProdukResponse>,
                response: Response<ListProdukResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listProduk.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
        })
    }

    
}




