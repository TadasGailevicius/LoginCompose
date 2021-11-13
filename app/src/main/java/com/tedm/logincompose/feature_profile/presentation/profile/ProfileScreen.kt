package com.tedm.logincompose.feature_profile.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tedm.logincompose.core.presentation.ui.theme.SpaceLarge
import com.tedm.logincompose.core.util.Constants

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
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
                    painter = rememberImagePainter(Constants.LOGO_URL),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                )
            }
            Text(text = "Name")
            Spacer(modifier = Modifier.height(SpaceLarge))
            Text(text = "Address")
            Spacer(modifier = Modifier.height(SpaceLarge))
            Text(text = "+37060277777")
        }
    }

}