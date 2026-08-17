package com.campusone.app.feature.auth.data.repository

import com.campusone.app.core.common.UiState
import com.campusone.app.core.common.UserRole
import com.campusone.app.core.datastore.AuthPreferences
import com.campusone.app.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences
) : AuthRepository {

    override fun login(email: String, password: String): Flow<UiState<UserRole>> = flow {
        emit(UiState.Loading)
        
        // Mock API Latency
        delay(1500)
        
        // Mock Logic: Any non-empty credentials work
        if (email.isNotBlank() && password.isNotBlank()) {
            val mockRole = if (email.contains("admin", ignoreCase = true)) {
                UserRole.SUPER_ADMIN
            } else {
                UserRole.STUDENT
            }
            
            authPreferences.saveAuthTokens("mock_access_token", "mock_refresh_token")
            authPreferences.saveUserRole(mockRole)
            
            emit(UiState.Success(mockRole))
        } else {
            emit(UiState.Error("Invalid email or password"))
        }
    }

    override suspend fun logout() {
        authPreferences.clearAuthData()
    }

    override fun isUserLoggedIn(): Boolean {
        return authPreferences.getAccessToken() != null
    }

    override fun getSavedRole(): UserRole {
        return authPreferences.getUserRole()
    }
}
