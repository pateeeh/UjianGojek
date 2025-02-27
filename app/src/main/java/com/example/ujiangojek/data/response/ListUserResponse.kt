package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(

	@field:SerializedName("ListUserResponse")
	val listUserResponse: List<ListUserResponseItem>
)