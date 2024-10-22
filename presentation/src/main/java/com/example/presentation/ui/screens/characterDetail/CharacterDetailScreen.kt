package com.example.presentation.ui.screens.characterDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.model.UICharacter
import com.example.presentation.theme.LocalDimens
import com.example.presentation.ui.components.NetworkErrorRetryView
import com.example.presentation.ui.components.NetworkImageView

@Composable
fun CharacterDetailScreen() {
    val viewModel: CharacterDetailViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (viewState.value) {
            is CharacterDetailMVIContract.CharacterDetailViewState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is CharacterDetailMVIContract.CharacterDetailViewState.Success -> {
                CharacterDetailView(uiCharacter = (viewState.value as CharacterDetailMVIContract.CharacterDetailViewState.Success).data)
            }

            is CharacterDetailMVIContract.CharacterDetailViewState.Failure -> {
                NetworkErrorRetryView {
                    viewModel.sendIntent(
                        CharacterDetailMVIContract.CharacterDetailViewIntent.LoadDetails(
                            viewModel.getCharacterId()
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterDetailView(uiCharacter: UICharacter) {
    val dimen = LocalDimens.current

    ConstraintLayout(
        modifier = Modifier
            .padding(dimen.medium)
            .fillMaxSize()
    ) {
        val (
            image, labelName, name, labelGender, gender,
            labelHair, hair, labelAge, age, labelOccupation, occupation
        ) = createRefs()
        val verticalFromStartGuideline = createGuidelineFromStart(0.35f)
        NetworkImageView(url = uiCharacter.image,
            description = stringResource(id = R.string.character_image_description),
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .fillMaxHeight(0.5f))
        // Name
        if (uiCharacter.name != "") {
            Text(text = stringResource(id = R.string.label_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(labelName) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                })
            Text(text = uiCharacter.name, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(verticalFromStartGuideline)
                })
        } else {
            Box(modifier = Modifier.constrainAs(name) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
            })
        }
        // Gender
        if (uiCharacter.gender != "") {
            Text(text = stringResource(id = R.string.label_gender),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(labelGender) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                })
            Text(text = uiCharacter.gender, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(gender) {
                    top.linkTo(name.bottom)
                    start.linkTo(verticalFromStartGuideline)
                })
        } else {
            Box(modifier = Modifier.constrainAs(gender) {
                top.linkTo(name.bottom)
                start.linkTo(parent.start)
            })
        }
        // Hair
        if (uiCharacter.hair != "") {
            Text(text = stringResource(id = R.string.label_hair),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(labelHair) {
                    top.linkTo(gender.bottom)
                    start.linkTo(parent.start)
                })
            Text(text = uiCharacter.hair, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(hair) {
                    top.linkTo(gender.bottom)
                    start.linkTo(verticalFromStartGuideline)
                })
        } else {
            Box(modifier = Modifier.constrainAs(hair) {
                top.linkTo(gender.bottom)
                start.linkTo(parent.start)
            })
        }
        // Age
        if (uiCharacter.age != "") {
            Text(text = stringResource(id = R.string.label_age),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(labelAge) {
                    top.linkTo(hair.bottom)
                    start.linkTo(parent.start)
                })
            Text(text = uiCharacter.age, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(age) {
                    top.linkTo(hair.bottom)
                    start.linkTo(verticalFromStartGuideline)
                })
        } else {
            Box(modifier = Modifier.constrainAs(age) {
                top.linkTo(hair.bottom)
                start.linkTo(parent.start)
            })
        }
        // Occupation
        if (uiCharacter.occupation != "") {
            Text(text = stringResource(id = R.string.label_occupation),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(labelOccupation) {
                    top.linkTo(age.bottom)
                    start.linkTo(parent.start)
                })
            Text(text = uiCharacter.occupation, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(occupation) {
                    top.linkTo(age.bottom)
                    start.linkTo(verticalFromStartGuideline)
                })
        } else {
            Box(modifier = Modifier.constrainAs(occupation) {
                top.linkTo(age.bottom)
                start.linkTo(parent.start)
            })
        }
    }
}