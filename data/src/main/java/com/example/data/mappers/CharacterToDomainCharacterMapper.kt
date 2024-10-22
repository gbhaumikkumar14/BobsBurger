package com.example.data.mappers

import com.example.data.dataSource.model.Character
import com.example.data.dataSource.model.Relative
import com.example.domain.model.DomainCharacter
import com.example.domain.model.DomainRelative
import javax.inject.Inject

class CharacterToDomainCharacterMapper @Inject constructor() {
    fun mapCharacterList(charactersList: List<Character>): List<DomainCharacter> =
        charactersList.map {
            with(it) {
                DomainCharacter(
                    age = age ?: "",
                    firstEpisode = firstEpisode ?: "",
                    gender = gender ?: "",
                    hair = hair ?: "",
                    id = id,
                    image = image ?: "",
                    name = name,
                    occupation = occupation ?: "",
                    relatives = mapRelatives(relativeList = relatives ?: listOf())
                )
            }
        }

    fun mapCharacter(character: Character): DomainCharacter =
        with(character) {
            DomainCharacter(
                age = age ?: "",
                firstEpisode = firstEpisode ?: "",
                gender = gender ?: "",
                hair = hair ?: "",
                id = id,
                image = image ?: "",
                name = name,
                occupation = occupation ?: "",
                relatives = mapRelatives(relativeList = relatives ?: listOf())
            )
        }

    private fun mapRelatives(relativeList: List<Relative>): List<DomainRelative> =
        relativeList.map {
            with(it) {
                DomainRelative(
                    id = _id,
                    name = name,
                    relationship = relationship ?: ""
                )
            }
        }
}