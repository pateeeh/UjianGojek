package com.example.ujiangojek.data.response

import com.google.gson.annotations.SerializedName

data class MenuItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("nama_menu")
	val namaMenu: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("gambar")
	val gambar: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)