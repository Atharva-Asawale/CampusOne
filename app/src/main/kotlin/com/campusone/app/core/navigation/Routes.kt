package com.campusone.app.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Splash : Route
    
    @Serializable
    data object Login : Route
    
    @Serializable
    data object StudentDashboard : Route
    
    @Serializable
    data object AdminDashboard : Route
    
    // Bottom Nav Routes
    @Serializable
    data object Home : Route
    
    @Serializable
    data object Events : Route
    
    @Serializable
    data object Clubs : Route
    
    @Serializable
    data object Chat : Route
    
    @Serializable
    data object Profile : Route
    
    // Other Feature Placeholders
    @Serializable
    data object Placements : Route
    
    @Serializable
    data object Sports : Route
}
