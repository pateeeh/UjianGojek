package com.example.ujiangojek.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoMartProduk (val image: Int, val namaBarang: String, val harga: String) : Parcelable