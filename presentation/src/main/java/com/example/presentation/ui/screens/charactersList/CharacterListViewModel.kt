package com.example.presentation.ui.screens.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.response.Result
import com.example.domain.usecase.GetAllCharacterUseCase
import com.example.presentation.di.IoDispatcher
import com.example.presentation.mapper.UICharacterMapper
import com.example.presentation.ui.base.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getAllCharacterUseCase: GetAllCharacterUseCase,
    private val uiCharacterMapper: UICharacterMapper
) : ViewModel(), CharacterListMVIContract {

    init {
        sendIntent(CharacterListMVIContract.CharactersListViewIntent.LoadData)
    }

    private val _viewState = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CharacterListMVIContract.CharactersListViewState>
        get() = _viewState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<CharacterListMVIContract.CharactersListSideEffect>()
    override val sideEffect: Flow<CharacterListMVIContract.CharactersListSideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is CharacterListMVIContract.CharactersListViewIntent.LoadData -> { fetchAllCharacters()}
            is CharacterListMVIContract.CharactersListViewIntent.OnCharacterClicked -> {
                viewModelScope.launch {
                    _sideEffect.emit(CharacterListMVIContract.CharactersListSideEffect.NavigateToDetailsScreen(intent.characterId, intent.characterName))
                }
            }
        }
    }

    override fun createInitialState(): CharacterListMVIContract.CharactersListViewState =
        CharacterListMVIContract.CharactersListViewState.Loading

    private fun fetchAllCharacters() {
        viewModelScope.launch(dispatcher) {
            when (val result = getAllCharacterUseCase.getAllCharacters()) {
                is Result.Success -> _viewState.emit(
                    CharacterListMVIContract.CharactersListViewState.Success(
                        uiCharacterMapper.mapToUiListModel(
                            result.data
                        )
                    )
                )

                is Result.Failure -> _viewState.emit(
                    CharacterListMVIContract.CharactersListViewState.Failure(result.exception)
                )

                is Result.Loading -> _viewState.emit(CharacterListMVIContract.CharactersListViewState.Loading)
            }
        }
    }
}