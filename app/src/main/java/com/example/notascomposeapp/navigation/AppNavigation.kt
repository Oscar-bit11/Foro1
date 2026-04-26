package com.example.notascomposeapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notascomposeapp.screens.HomeScreen
import com.example.notascomposeapp.screens.LoginScreen
import com.example.notascomposeapp.screens.RegisterScreen
import com.example.notascomposeapp.screens.ResultScreen
import com.example.notascomposeapp.viewmodel.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = { navController.navigate("home") },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                authViewModel = authViewModel,
                onBackToLogin = { navController.popBackStack() }
            )
        }

        composable("home") {
            HomeScreen(
                onCalculate = { studentName, note1, note2, note3, average, status ->
                    val route = "result/${Uri.encode(studentName)}/$note1/$note2/$note3/$average/${Uri.encode(status)}"
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = "result/{name}/{note1}/{note2}/{note3}/{average}/{status}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("note1") { type = NavType.FloatType },
                navArgument("note2") { type = NavType.FloatType },
                navArgument("note3") { type = NavType.FloatType },
                navArgument("average") { type = NavType.FloatType },
                navArgument("status") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ResultScreen(
                studentName = backStackEntry.arguments?.getString("name").orEmpty(),
                note1 = backStackEntry.arguments?.getFloat("note1") ?: 0f,
                note2 = backStackEntry.arguments?.getFloat("note2") ?: 0f,
                note3 = backStackEntry.arguments?.getFloat("note3") ?: 0f,
                average = backStackEntry.arguments?.getFloat("average") ?: 0f,
                status = backStackEntry.arguments?.getString("status").orEmpty(),
                onNewCalculation = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
