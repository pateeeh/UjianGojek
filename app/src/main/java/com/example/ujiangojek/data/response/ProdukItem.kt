package com.example.ujiangojek.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProdukItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("nama_produk")
	val namaProduk: String,

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
): Parcelable