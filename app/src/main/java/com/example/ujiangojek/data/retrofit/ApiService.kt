package com.example.ujiangojek.data.retrofit

import com.example.ujiangojek.data.response.ListInboxResponse
import com.example.ujiangojek.data.response.ListPosterResponse
import com.example.ujiangojek.data.response.ListProdukResponse
import com.example.ujiangojek.data.response.ListUserResponse
import com.example.ujiangojek.data.response.ListUserResponseItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/poster")
    fun getAllPoster(): Call<ListPosterResponse>

    @GET("api/v1/user")
    fun getUserProfile(): Call<List<ListUserResponseItem>>

    @FormUrlEncoded
    @PUT("api/v1/user/update/2")
    fun updateUserProfile(
        @Query("id") id: Int,
        @Field("nama") nama: String,
        @Field("no_telp") noTelp: String,
        @Field("email") email: String
    ): Call<ListUserResponse>

    @GET("api/v1/produk")
    fun getAllProduk(): Call<ListProdukResponse>

    @GET("api/v1/inbox")
    fun getAllInbox(): Call<ListInboxResponse>

}