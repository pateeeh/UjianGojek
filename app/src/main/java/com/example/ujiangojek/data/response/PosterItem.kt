package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class PosterItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("foto_poster")
	val fotoPoster: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)