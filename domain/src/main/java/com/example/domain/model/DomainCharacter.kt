package com.example.domain.model


data class DomainCharacter(
    val age: String,
    val firstEpisode: String,
    val gender: String,
    val hair: String,
    val id: Int,
    val image: String,
    val name: String,
    val occupation: String,
    val relatives: List<DomainRelative>,
)
