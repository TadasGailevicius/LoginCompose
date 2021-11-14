package com.tedm.logincompose.feature_profile.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tedm.logincompose.core.presentation.ui.theme.SpaceLarge
import com.tedm.logincompose.core.presentation.util.UiEvent
import com.tedm.logincompose.core.presentation.util.asString
import com.tedm.logincompose.core.util.Constants
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.getProfile()
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = 50.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Image(
                    painter = rememberImagePainter(state.profile?.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(256.dp)
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = state.profile?.fullName ?: ""
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = state.profile?.address ?: ""
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = state.profile?.phone ?: ""
                )
            }
        }
    }

}