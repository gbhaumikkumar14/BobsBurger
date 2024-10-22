package com.example.presentation.ui.screens.characterDetail

import com.example.presentation.model.UICharacter
import com.example.presentation.ui.base.MVIBaseContract
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState

interface CharacterDetailMVIContract: MVIBaseContract<ViewState, ViewIntent, SideEffect> {
    sealed interface CharacterDetailViewState: ViewState{
        data object Loading : CharacterDetailViewState
        class Success(val data: UICharacter): CharacterDetailViewState
        class Failure(val throwable: Throwable): CharacterDetailViewState
    }

    sealed interface CharacterDetailViewIntent: ViewIntent{
        data class LoadDetails(val id: Int) : CharacterDetailViewIntent
    }

    sealed interface CharacterDetailSideEffect : SideEffect{

    }
}