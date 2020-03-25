package org.wit.quoteme.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteMeModel(var id: Long = 0, var title: String = "") : Parcelable