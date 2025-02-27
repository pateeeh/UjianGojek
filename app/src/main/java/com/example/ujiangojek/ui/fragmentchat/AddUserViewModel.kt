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

class AddUserViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _addResponse = MutableLiveData<String>()
    val addResponse: LiveData<String> = _addResponse

    fun insertSiswa(nama: String, noTelp: String, email: String, foto: String) {
        _isLoading.value = true

        // 🔍 Log data sebelum request
        Log.d("InsertSiswa", "Nama: $nama, NoTelp: $noTelp, Email: $email, Foto: $foto")

        val client = ApiConfig.getApiService().addUser(nama, noTelp, email, foto)
        client.enqueue(object : Callback<ListUserResponseItem> {
            override fun onResponse(
                call: Call<ListUserResponseItem>,
                response: Response<ListUserResponseItem>
            ) {
                _isLoading.value = false

                // 🔍 Log response dari server
                Log.d("InsertSiswa", "Response Code: ${response.code()}")
                Log.d("InsertSiswa", "Response Error: ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    _addResponse.value = "User Berhasil Ditambahkan"
                } else {
                    _addResponse.value = "Failed: ${response.code()} - ${response.errorBody()?.string()}"
                }
            }

            override fun onFailure(call: Call<ListUserResponseItem>, t: Throwable) {
                _isLoading.value = false
                Log.e("InsertSiswa", "onFailure: ${t.message}")
                _addResponse.value = "onFailure: ${t.message}"
            }
        })
    }
}