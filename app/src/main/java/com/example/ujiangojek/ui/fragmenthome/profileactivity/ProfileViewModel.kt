package com.example.ujiangojek.ui.fragmenthome.profileactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListUserResponse
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _profile = MutableLiveData<ListUserResponseItem?>()
    val profile: LiveData<ListUserResponseItem?> get() = _profile

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getProfile() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserProfile()
        client.enqueue(object : Callback<List<ListUserResponseItem>> {
            override fun onResponse(
                call: Call<List<ListUserResponseItem>>,
                response: Response<List<ListUserResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    Log.d("ProfileViewModel", "Response: $responseBody")

                    if (!responseBody.isNullOrEmpty()) {
                        _profile.postValue(responseBody[0]) // Ambil user pertama
                    } else {
                        Log.e("ProfileViewModel", "Data kosong!")
                    }
                } else {
                    Log.e("ProfileViewModel", "Response gagal: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ListUserResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("ProfileViewModel", "onFailure: ${t.message}")
            }
        })
    }
}
