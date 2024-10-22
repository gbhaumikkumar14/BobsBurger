package com.example.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UICharacter(
    val age: String,
    val firstEpisode: String,
    val gender: String,
    val hair: String,
    val id: Int,
    val image: String,
    val name: String,
    val occupation: String,
    val relatives: List<UIRelative>,
): Parcelable