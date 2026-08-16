package com.campusone.app.feature.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.campusone.app.core.common.UserRole
import com.campusone.app.core.designsystem.Dimensions
import com.campusone.app.core.designsystem.components.CampusOneButton

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: (UserRole) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onLoginSuccess(uiState.selectedRole)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Campus One",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Dimensions.PaddingLarge))
        
        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimensions.PaddingSmall))
        
        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimensions.PaddingMedium))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = uiState.selectedRole == UserRole.STUDENT,
                onClick = { viewModel.onRoleChanged(UserRole.STUDENT) }
            )
            Text("Student")
            Spacer(modifier = Modifier.width(Dimensions.PaddingMedium))
            RadioButton(
                selected = uiState.selectedRole != UserRole.STUDENT,
                onClick = { viewModel.onRoleChanged(UserRole.CLUB_ADMIN) }
            )
            Text("Admin")
        }
        
        Spacer(modifier = Modifier.height(Dimensions.PaddingLarge))
        
        CampusOneButton(
            text = if (uiState.isLoading) "Logging in..." else "Login",
            onClick = viewModel::login,
            enabled = !uiState.isLoading
        )
        
        if (uiState.error != null) {
            Text(
                text = uiState.error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = Dimensions.PaddingSmall)
            )
        }
    }
}
