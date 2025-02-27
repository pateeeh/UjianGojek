package com.example.ujiangojek.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ListInboxResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("foto_poster")
	val fotoPoster: String,

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi_promo")
	val deskripsiPromo: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)