package org.wit.flowers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlowerModel(var id: Long = 0, var name: String = "", var information: String = "", var image: String = "") : Parcelable