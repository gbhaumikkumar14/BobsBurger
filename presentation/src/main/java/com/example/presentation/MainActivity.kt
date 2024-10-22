package com.example.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.example.presentation.theme.BobsBurgerTheme
import com.example.presentation.theme.LocalDimens
import com.example.presentation.ui.navigation.LocalNavController
import com.example.presentation.ui.navigation.NavGraph
import com.example.presentation.ui.navigation.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BobsBurgerApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BobsBurgerApp() {
    val navHostController = rememberNavController()
    val appBarTitle = remember {
        mutableStateOf("")
    }
    val show = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        navHostController.currentBackStackEntryFlow.collect {
            show.value = navHostController.currentDestination?.route != Routes.LIST_SCREEN
        }
    }
    BobsBurgerTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(
                    text = appBarTitle.value,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                MaterialTheme.colorScheme.primary
            ), navigationIcon = {
                if (show.value) {
                    Image(painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = LocalDimens.current.small, end = LocalDimens.current.small
                            )
                            .size(
                                LocalDimens.current.custom24, LocalDimens.current.custom24
                            )
                            .clickable {
                                navHostController.popBackStack()
                            })
                }
            })
        }, content = { padding ->
            // LocalNavController to get access to navController in all the screens as LocalNavController.current
            CompositionLocalProvider(LocalNavController provides navHostController) {
                NavGraph(modifier = Modifier.padding(padding)) {
                    appBarTitle.value = it
                }
            }
        })
    }
}

