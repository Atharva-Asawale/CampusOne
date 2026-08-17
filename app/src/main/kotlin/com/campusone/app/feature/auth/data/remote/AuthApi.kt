package com.campusone.app.feature.auth.data.remote

import com.campusone.app.feature.auth.data.remote.dto.LoginRequest
import com.campusone.app.feature.auth.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
