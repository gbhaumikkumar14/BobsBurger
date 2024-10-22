package com.example.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIRelative(
    val id: String,
    val name: String,
    val relationship: String,
): Parcelable