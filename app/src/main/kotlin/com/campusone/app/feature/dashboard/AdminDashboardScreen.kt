package com.campusone.app.feature.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campusone.app.core.designsystem.Dimensions
import com.campusone.app.core.designsystem.components.CampusOneButton

@Composable
fun AdminDashboardScreen(
    onLogout: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(Dimensions.PaddingLarge)
        ) {
            Text(
                text = "Admin Dashboard",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Analytics, User Management, and Settings will appear here.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = Dimensions.PaddingMedium)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            CampusOneButton(
                text = "Logout",
                onClick = onLogout
            )
        }
    }
}
