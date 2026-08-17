package com.campusone.app.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusone.app.core.common.UiState
import com.campusone.app.core.common.UserRole
import com.campusone.app.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun checkAuthStatus(onResult: (Boolean, UserRole) -> Unit) {
        viewModelScope.launch {
            if (authRepository.isUserLoggedIn()) {
                onResult(true, authRepository.getSavedRole())
            } else {
                onResult(false, UserRole.STUDENT)
            }
        }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, error = null) }
    }

    fun onRoleChanged(role: UserRole) {
        _uiState.update { it.copy(selectedRole = role) }
    }

    fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            authRepository.login(email, password).collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is UiState.Success -> {
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true,
                                selectedRole = state.data
                            ) 
                        }
                    }
                    is UiState.Error -> {
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                error = state.message
                            ) 
                        }
                    }
                }
            }
        }
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            authRepository.logout()
            _uiState.update { AuthUiState() } // Reset state
            onComplete()
        }
    }
}
