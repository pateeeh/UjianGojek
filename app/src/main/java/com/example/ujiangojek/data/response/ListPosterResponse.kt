package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class ListPosterResponse(

	@field:SerializedName("data")
	val data: List<PosterItem>,

	@field:SerializedName("message")
	val message: String
)