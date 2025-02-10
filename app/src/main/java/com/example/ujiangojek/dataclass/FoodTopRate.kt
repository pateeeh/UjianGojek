package com.example.ujiangojek.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodTopRate (val image: Int, val namaToko: String) : Parcelable
