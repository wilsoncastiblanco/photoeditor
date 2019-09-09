package com.example.photoeditor

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val id: String,
                 val urls: Urls): Parcelable