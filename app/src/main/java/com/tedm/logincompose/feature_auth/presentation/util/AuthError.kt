package com.tedm.logincompose.feature_auth.presentation.util

import com.tedm.logincompose.core.util.Error

sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail: AuthError()
    object InvalidPassword : AuthError()
}
