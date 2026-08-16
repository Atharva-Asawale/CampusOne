package com.campusone.app.feature.auth

import com.campusone.app.core.common.UserRole

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val selectedRole: UserRole = UserRole.STUDENT,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false
)
