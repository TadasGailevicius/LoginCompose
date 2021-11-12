package com.tedm.logincompose.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showToolbar: Boolean = true,
    showBackArrow: Boolean = true,
    navActions: @Composable RowScope.() -> Unit = {},
    toolbarTitle: String? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
        content()
    }

}