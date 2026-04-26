package com.example.notascomposeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var registeredUser by mutableStateOf("")
        private set

    var registeredPassword by mutableStateOf("")
        private set

    fun registerUser(username: String, password: String) {
        registeredUser = username.trim()
        registeredPassword = password
    }

    fun loginUser(username: String, password: String): String? {
        val inputUser = username.trim()

        return when {
            inputUser.isBlank() || password.isBlank() -> "Completa todos los campos."
            registeredUser.isBlank() -> "No hay usuarios registrados."
            inputUser != registeredUser -> "El usuario no existe."
            password != registeredPassword -> "Contraseña incorrecta."
            else -> null
        }
    }
}
