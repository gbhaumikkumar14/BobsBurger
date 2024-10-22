package com.example.presentation.mapper

import com.example.domain.model.DomainCharacter
import com.example.domain.model.DomainRelative
import com.example.presentation.model.UICharacter
import com.example.presentation.model.UIRelative
import javax.inject.Inject

class UICharacterMapper @Inject constructor() {
    fun mapToUiListModel(characterList: List<DomainCharacter>): List<UICharacter> =
        characterList.map {
            with(it) {
                UICharacter(
                    age = age,
                    firstEpisode = firstEpisode,
                    gender = gender,
                    hair = hair,
                    id = id,
                    image = image,
                    name = name,
                    occupation = occupation,
                    relatives = mapRelatives(relativeList = relatives)
                )
            }
        }

    fun mapToUiModel(character: DomainCharacter): UICharacter =
    with(character) {
        UICharacter(
            age = age,
            firstEpisode = firstEpisode,
            gender = gender,
            hair = hair,
            id = id,
            image = image,
            name = name,
            occupation = occupation,
            relatives = mapRelatives(relativeList = relatives)
        )
    }

    private fun mapRelatives(relativeList: List<DomainRelative>): List<UIRelative> =
        relativeList.map {
            with(it) {
                UIRelative(
                    id = id,
                    name = name,
                    relationship = relationship
                )
            }
        }
}


