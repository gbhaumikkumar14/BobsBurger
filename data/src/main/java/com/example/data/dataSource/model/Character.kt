package com.example.data.dataSource.model

data class Character(
    val age: String?,
    val allOccupations: List<String>?,
    val firstEpisode: String?,
    val gender: String?,
    val hair: String?,
    val id: Int,
    val image: String?,
    val name: String,
    val occupation: String?,
    val relatives: List<Relative>?,
    val url: String?,
    val voicedBy: String?,
    val wikiUrl: String?
)