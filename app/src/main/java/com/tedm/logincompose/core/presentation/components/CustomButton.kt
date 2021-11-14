package com.tedm.logincompose.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tedm.logincompose.core.presentation.ui.theme.SpaceMedium
import com.tedm.logincompose.core.presentation.ui.theme.SpaceSmall
import com.tedm.logincompose.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .padding(SpaceSmall)
    ) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .clickable {
                    onButtonClick()
                }
                .background(Color.LightGray.copy(alpha = 0.35f)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(
                    start = 48.dp,
                    bottom = SpaceMedium,
                    top = SpaceMedium,
                    end = 48.dp
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = Color.White
                )
            }
        }
    }

}