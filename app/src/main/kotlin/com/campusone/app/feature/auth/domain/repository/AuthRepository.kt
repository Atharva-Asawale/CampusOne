package com.campusone.app.feature.auth.domain.repository

import com.campusone.app.core.common.UiState
import com.campusone.app.core.common.UserRole
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<UiState<UserRole>>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
    fun getSavedRole(): UserRole
}
