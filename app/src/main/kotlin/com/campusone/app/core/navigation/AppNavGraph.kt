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

    NavHost(
        navController = navController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Route.Login> {
            val viewModel: AuthViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { role ->
                    val dest = if (role == UserRole.STUDENT) Route.StudentDashboard else Route.AdminDashboard
                    navController.navigate(dest) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Route.StudentDashboard> {
            StudentDashboardScreen()
        }
        
        composable<Route.AdminDashboard> {
            AdminDashboardScreen()
        }
    }
}
