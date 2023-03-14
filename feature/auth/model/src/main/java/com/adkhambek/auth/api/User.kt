package com.adkhambek.auth.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val name: String,
    val photo: String,
) : Parcelable
