package com.campusone.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusone.app.core.common.UserRole
import com.campusone.app.feature.auth.AuthViewModel
import com.campusone.app.feature.auth.LoginScreen
import com.campusone.app.feature.auth.SplashScreen
import com.campusone.app.feature.dashboard.AdminDashboardScreen
import com.campusone.app.feature.dashboard.StudentDashboardScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashScreen(
                viewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                },
                onNavigateToDashboard = { role ->
                    val dest = if (role == UserRole.STUDENT) Route.StudentDashboard else Route.AdminDashboard
                    navController.navigate(dest) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Route.Login> {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { role ->
                    val dest = if (role == UserRole.STUDENT) Route.StudentDashboard else Route.AdminDashboard
                    navController.navigate(dest) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Route.StudentDashboard> {
            StudentDashboardScreen(
                onLogout = {
                    authViewModel.logout {
                        navController.navigate(Route.Login) {
                            popUpTo(Route.StudentDashboard) { inclusive = true }
                        }
                    }
                }
            )
        }
        
        composable<Route.AdminDashboard> {
            AdminDashboardScreen(
                onLogout = {
                    authViewModel.logout {
                        navController.navigate(Route.Login) {
                            popUpTo(Route.AdminDashboard) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
