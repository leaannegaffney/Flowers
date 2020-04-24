package org.wit.quoteme.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(var id: Long = 0, var text: String = "") : Parcelable