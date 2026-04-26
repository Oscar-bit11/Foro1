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
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onBackToLogin: () -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de usuario",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nuevo usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Nueva contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                errorMessage?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        errorMessage = when {
                            username.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                                "Completa todos los campos."
                            }
                            password != confirmPassword -> "Las contraseñas no coinciden."
                            else -> null
                        }

                        if (errorMessage == null) {
                            authViewModel.registerUser(username, password)
                            onBackToLogin()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear cuenta")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onBackToLogin,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver al login")
                }
            }
        }
    }
}
