package com.tedm.logincompose.core.presentation.util

import com.tedm.logincompose.core.util.Event
import com.tedm.logincompose.core.util.UiText

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin: UiEvent()
}
