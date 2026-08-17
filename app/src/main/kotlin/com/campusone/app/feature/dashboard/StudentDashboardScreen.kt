package com.campusone.app.feature.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.campusone.app.core.navigation.Route
import com.campusone.app.feature.events.EventsScreen
import com.campusone.app.feature.clubs.ClubsScreen
import com.campusone.app.feature.chat.ChatScreen
import com.campusone.app.feature.profile.ProfileScreen

sealed class BottomNavItem(
    val route: Route,
    val icon: ImageVector,
    val label: String
) {
    data object Home : BottomNavItem(Route.Home, Icons.Default.Home, "Home")
    data object Events : BottomNavItem(Route.Events, Icons.Default.Event, "Events")
    data object Clubs : BottomNavItem(Route.Clubs, Icons.Default.Groups, "Clubs")
    data object Chat : BottomNavItem(Route.Chat, Icons.AutoMirrored.Filled.Chat, "Chat")
    data object Profile : BottomNavItem(Route.Profile, Icons.Default.Person, "Profile")
}

@Composable
fun StudentDashboardScreen(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Events,
        BottomNavItem.Clubs,
        BottomNavItem.Chat,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route?.contains(item.route::class.qualifiedName ?: "") == true } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Home> { HomeScreen() }
            composable<Route.Events> { EventsScreen() }
            composable<Route.Clubs> { ClubsScreen() }
            composable<Route.Chat> { ChatScreen() }
            composable<Route.Profile> { 
                ProfileScreen(onLogout = onLogout) 
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Surface {
        Text("Home Screen Placeholder - Student Dashboard")
    }
}
