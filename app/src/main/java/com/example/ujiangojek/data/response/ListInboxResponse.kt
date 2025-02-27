package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class ListInboxResponse(

	@field:SerializedName("data")
	val data: List<ListInboxResponseItem>,

	@field:SerializedName("message")
	val message: String
)