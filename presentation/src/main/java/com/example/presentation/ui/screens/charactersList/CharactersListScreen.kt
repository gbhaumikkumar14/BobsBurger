package com.example.presentation.ui.screens.charactersList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.model.UICharacter
import com.example.presentation.theme.Grey
import com.example.presentation.theme.LocalDimens
import com.example.presentation.ui.components.NetworkErrorRetryView
import com.example.presentation.ui.components.NetworkImageView
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CharacterListScreen(onItemClick: (Int, String) -> Unit) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    val data = viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest {
            if (it is CharacterListMVIContract.CharactersListSideEffect.NavigateToDetailsScreen) {
                onItemClick(it.characterId, it.characterName)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (data.value) {
            is CharacterListMVIContract.CharactersListViewState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is CharacterListMVIContract.CharactersListViewState.Success -> {
                CharacterList(data = (data.value as
                        CharacterListMVIContract.CharactersListViewState.Success).charactersList,
                    onClick = { id, name ->
                        viewModel.sendIntent(
                            CharacterListMVIContract.CharactersListViewIntent.OnCharacterClicked(
                                id,
                                name
                            )
                        )
                    })
            }

            is CharacterListMVIContract.CharactersListViewState.Failure -> {
                NetworkErrorRetryView {
                    viewModel.sendIntent(CharacterListMVIContract.CharactersListViewIntent.LoadData)
                }
            }

        }
    }
}

@Composable
private fun CharacterList(data: List<UICharacter>, onClick: (Int, String) -> Unit) {
    val dimen = LocalDimens.current
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(data) {
            CharacterListItem(uiCharacter = it, onClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = dimen.medium))
        }
    }
}

@Composable
private fun CharacterListItem(uiCharacter: UICharacter, onClick: (Int, String) -> Unit) {
    val dimen = LocalDimens.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(dimen.medium)
            .fillMaxWidth()
            .clickable { onClick(uiCharacter.id, uiCharacter.name) }
    ) {
        NetworkImageView(
            url = uiCharacter.image,
            description = stringResource(id = R.string.character_image_description),
            modifier = Modifier
                .background(color = Color.White, shape = CircleShape)
                .clip(CircleShape)
                .width(dimen.custom100)
                .height(dimen.custom100)
        )
        Column(
            modifier = Modifier.padding(dimen.medium)
        ) {
            Text(text = uiCharacter.name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = uiCharacter.gender,
                style = MaterialTheme.typography.bodyMedium,
                color = Grey
            )
        }
    }
}