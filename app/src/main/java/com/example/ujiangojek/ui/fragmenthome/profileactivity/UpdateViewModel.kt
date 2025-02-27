package com.example.ujiangojek.ui.fragmenthome.profileactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujiangojek.data.response.ListUserResponse
import com.example.ujiangojek.data.response.ListUserResponseItem
import com.example.ujiangojek.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _updateResponse = MutableLiveData<String>()
    val updateResponse: LiveData<String> = _updateResponse

    fun updateUserProfile(id: Int, nama: String, noTelp: String, email: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().updateUserProfile(id, nama, noTelp, email)
        client.enqueue(object : Callback<ListUserResponse> {
            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                _isLoading.value = false
                _updateResponse.value = "onFailure: ${t.message}"
            }

            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful){
                    val responsBody = response.body()
                    if (responsBody != null){
                        _updateResponse.value = "Update User Berhasil"
                    }else {
                        _updateResponse.value = "null"
                    }
                } else {
                    _updateResponse.value = "failed"
                }
            }

        })
    }
}