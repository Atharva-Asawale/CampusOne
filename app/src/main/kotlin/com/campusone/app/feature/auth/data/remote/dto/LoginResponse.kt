package com.campusone.app.feature.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val name: String,
    val role: String
)
