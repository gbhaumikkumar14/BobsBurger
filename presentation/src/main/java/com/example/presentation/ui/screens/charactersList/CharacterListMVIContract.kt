package com.example.presentation.ui.screens.charactersList

import com.example.presentation.model.UICharacter
import com.example.presentation.ui.base.MVIBaseContract
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState

interface CharacterListMVIContract: MVIBaseContract<ViewState, ViewIntent, SideEffect> {
    sealed interface CharactersListViewState: ViewState{
        data object Loading : CharactersListViewState

        class Success(val charactersList: List<UICharacter>): CharactersListViewState

        class Failure(val throwable: Throwable): CharactersListViewState
    }

    sealed interface CharactersListViewIntent: ViewIntent{
        data object LoadData: CharactersListViewIntent

        class OnCharacterClicked(val characterId: Int, val characterName: String): CharactersListViewIntent
    }

    sealed interface CharactersListSideEffect : SideEffect{
        class NavigateToDetailsScreen(val characterId: Int, val characterName: String): CharactersListSideEffect
    }
}