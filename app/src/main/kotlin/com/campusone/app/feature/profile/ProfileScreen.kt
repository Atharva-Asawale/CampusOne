package com.campusone.app.feature.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.campusone.app.core.designsystem.Dimensions
import com.campusone.app.core.designsystem.components.CampusOneButton

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.PaddingLarge),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Profile Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            CampusOneButton(
                text = "Logout",
                onClick = onLogout
            )
        }
    }
}
