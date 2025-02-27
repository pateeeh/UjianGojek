package com.example.ujiangojek.data.retrofit

import com.example.ujiangojek.data.response.ListInboxResponse
import com.example.ujiangojek.data.response.ListPosterResponse
import com.example.ujiangojek.data.response.ListProdukResponse
import com.example.ujiangojek.data.response.ListUserResponse
import com.example.ujiangojek.data.response.ListUserResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/poster")
    fun getAllPoster(): Call<ListPosterResponse>

    @GET("api/v1/user")
    fun getUserProfile(): Call<List<ListUserResponseItem>>

    @GET("api/v1/produk")
    fun getAllProduk(): Call<ListProdukResponse>

    @GET("api/v1/inbox")
    fun getAllInbox(): Call<ListInboxResponse>

}