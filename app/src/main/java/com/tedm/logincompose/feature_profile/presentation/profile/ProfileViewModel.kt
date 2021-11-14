package com.tedm.logincompose.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tedm.logincompose.core.presentation.util.UiEvent
import com.tedm.logincompose.core.util.Event
import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.core.util.UiText
import com.tedm.logincompose.feature_profile.domain.repository.ProfileRepository
import com.tedm.logincompose.feature_profile.domain.use_case.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val repository: ProfileRepository,
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> {
                profileUseCases.logout()
                viewModelScope.launch {
                    repository.deleteUser()
                }
            }
            ProfileEvent.ShowLogoutDialog -> {
                _state.value = state.value.copy(
                    isLogoutDialogVisible = true
                )
            }
            is ProfileEvent.DismissLogoutDialog -> {
                _state.value = state.value.copy(
                    isLogoutDialogVisible = false
                )
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when (val result = profileUseCases.getProfile()) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        profile = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            uiText = result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}