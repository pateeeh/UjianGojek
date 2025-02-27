package com.example.ujiangojek.ui.fragmentchat.icon

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListInboxResponse
import com.example.ujiangojek.data.response.ListInboxResponseItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxViewModel:ViewModel() {
    private val _listInbox =MutableLiveData<List<ListInboxResponseItem>>()
    val listInbox : LiveData<List<ListInboxResponseItem>> =_listInbox

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        findAllInbox()
    }

    private fun findAllInbox() {
        _isLoading.value = false
        val client: Call<ListInboxResponse> = ApiConfig.getApiService().getAllInbox()
        client.enqueue(object : Callback<ListInboxResponse> { // Perhatikan perubahan di sini
            override fun onFailure(call: Call<ListInboxResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(
                call: Call<ListInboxResponse>, // Perhatikan perubahan di sini
                response: Response<ListInboxResponse> // Perhatikan perubahan di sini
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listInbox.value = responseBody.data // Perhatikan perubahan di sini
                    } else {
                        Log.e(TAG, "onResponse: Response body is null")
                    }
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }
        })
    }
}

