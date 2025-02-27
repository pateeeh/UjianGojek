package com.example.ujiangojek.ui.fragmentchat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel : ViewModel() {
    private val _chatList = MutableLiveData<List<ListUserResponseItem>>()
    val chatList: LiveData<List<ListUserResponseItem>> get() = _chatList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        findAllChat()
    }

    private fun findAllChat() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserProfile()
        client.enqueue(object : Callback<List<ListUserResponseItem>> {
            override fun onResponse(
                call: Call<List<ListUserResponseItem>>,
                response: Response<List<ListUserResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _chatList.value = response.body() ?: emptyList()
                } else {
                    Log.e("ChatViewModel", "Response gagal: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListUserResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("ChatViewModel", "onFailure: ${t.message}")
            }
        })
    }
}
