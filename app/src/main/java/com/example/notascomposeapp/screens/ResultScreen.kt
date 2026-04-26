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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun ResultScreen(
    studentName: String,
    carnet: String,
    subject: String,
    term: String,
    note1: Float,
    note2: Float,
    note3: Float,
    average: Float,
    status: String,
    onNewCalculation: () -> Unit
) {
    val customMessage = if (status == "Aprobado") {
        "Excelente trabajo, $studentName. Has alcanzado el rendimiento esperado en $subject."
    } else {
        "Ánimo, $studentName. Puedes reforzar tus contenidos de $subject y mejorar en el próximo período."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Promedio Estudiantil",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Resumen de desempeño académico",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Estudiante: $studentName", style = MaterialTheme.typography.titleMedium)
                Text("Carnet: $carnet")
                Text("Materia: $subject")
                Text("Ciclo académico: $term")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nota 1: ${format(note1)}")
                Text("Nota 2: ${format(note2)}")
                Text("Nota 3: ${format(note3)}")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Promedio final: ${format(average)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Estado académico: $status",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (status == "Aprobado") {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(customMessage)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNewCalculation, modifier = Modifier.fillMaxWidth()) {
            Text("Registrar nuevas calificaciones")
        }
    }
}

private fun format(value: Float): String = String.format(Locale.US, "%.2f", value)
