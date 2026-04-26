package com.example.notascomposeapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.notascomposeapp.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "NotasComposeApp",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Bienvenido 👋",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                errorMessage?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val loginError = authViewModel.loginUser(username, password)
                        if (loginError == null) {
                            errorMessage = null
                            onLoginSuccess()
                        } else {
                            errorMessage = loginError
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ingresar")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrarse")
                }
            }
        }
    }
}
