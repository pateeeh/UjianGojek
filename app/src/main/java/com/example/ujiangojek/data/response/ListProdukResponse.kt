package com.example.ujiangojek.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListProdukResponse(

    @field:SerializedName("data")
	val data: List<ProdukItem>,

    @field:SerializedName("message")
	val message: String
): Parcelable