package com.tedm.logincompose.feature_profile.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tedm.logincompose.core.presentation.ui.theme.SpaceLarge
import com.tedm.logincompose.core.presentation.ui.theme.SpaceMedium
import com.tedm.logincompose.core.presentation.util.UiEvent
import com.tedm.logincompose.core.presentation.util.asString
import kotlinx.coroutines.flow.collectLatest
import com.tedm.logincompose.R
import com.tedm.logincompose.core.presentation.components.StandardToolbar
import com.tedm.logincompose.core.presentation.ui.theme.SpaceSmall

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    onLogout: () -> Unit = {},
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

    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
            navActions = {
                Text(
                    text = "Logout",
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                        }
                        .padding(end = SpaceMedium)
                )
            }
        )
        Spacer(modifier = Modifier.height(SpaceSmall))

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

            if (state.isLogoutDialogVisible) {
                Dialog(onDismissRequest = {
                    viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                }) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.surface,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(SpaceMedium)
                    ) {
                        Text(text = stringResource(id = R.string.do_you_want_to_logout))
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.align(End)
                        ) {
                            Text(
                                text = stringResource(id = R.string.no).uppercase(),
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                }
                            )
                            Spacer(modifier = Modifier.width(SpaceMedium))
                            Text(
                                text = stringResource(id = R.string.yes).uppercase(),
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(ProfileEvent.Logout)
                                    viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                    onLogout()
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}