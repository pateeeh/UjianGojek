package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class ListMenuResponse(

	@field:SerializedName("data")
	val data: List<MenuItem>,

	@field:SerializedName("message")
	val message: String
)