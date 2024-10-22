package com.example.presentation.ui.screens.characterDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.response.Result
import com.example.domain.usecase.GetCharacterDetailUseCase
import com.example.presentation.di.IoDispatcher
import com.example.presentation.mapper.UICharacterMapper
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState
import com.example.presentation.ui.navigation.ArgKeys
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
class CharacterDetailViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val mapper: UICharacterMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel(), CharacterDetailMVIContract {
    private val _characterId = mutableStateOf(Int.MIN_VALUE)
    fun getCharacterId(): Int{
        return _characterId.value
    }

    init {
        savedStateHandle.get<Int>(ArgKeys.KEY_DETAIL_CHARACTER_ID)?.let{
            _characterId.value = it
            sendIntent(CharacterDetailMVIContract.CharacterDetailViewIntent.LoadDetails(it))
        }
    }

    override fun createInitialState(): ViewState  = CharacterDetailMVIContract.CharacterDetailViewState.Loading

    private val _viewState = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<ViewState>
        get() = _viewState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<CharacterDetailMVIContract.CharacterDetailSideEffect>()
    override val sideEffect: Flow<SideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: ViewIntent) {
        when(intent){
            is CharacterDetailMVIContract.CharacterDetailViewIntent.LoadDetails -> {
                fetchCharacterDetails(intent.id)
            }
        }
    }

    private fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch(dispatcher) {
            when (val result = getCharacterDetailUseCase.getCharacterDetail(id)) {
                is Result.Success -> _viewState.emit(
                    CharacterDetailMVIContract.CharacterDetailViewState.Success(
                        mapper.mapToUiModel(result.data)
                    ))

                is Result.Failure -> {
                    _viewState.emit(
                        CharacterDetailMVIContract.CharacterDetailViewState.Failure(result.exception)
                    )
                }

                is Result.Loading -> {
                    _viewState.emit(CharacterDetailMVIContract.CharacterDetailViewState.Loading)
                }
            }
        }
    }


}