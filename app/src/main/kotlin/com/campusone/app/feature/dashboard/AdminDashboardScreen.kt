package com.campusone.app.feature.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.campusone.app.core.designsystem.Dimensions

@Composable
fun AdminDashboardScreen() {
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
        }
    }
}
