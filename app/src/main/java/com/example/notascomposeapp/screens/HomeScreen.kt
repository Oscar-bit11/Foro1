package com.example.notascomposeapp.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onCalculate: (String, String, String, String, Float, Float, Float, Float, String) -> Unit
) {
    var studentName by remember { mutableStateOf("") }
    var carnet by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var term by remember { mutableStateOf("") }
    var note1 by remember { mutableStateOf("") }
    var note2 by remember { mutableStateOf("") }
    var note3 by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Promedio Estudiantil",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Registro académico de calificaciones",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Información del estudiante",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = carnet,
                    onValueChange = { carnet = it },
                    label = { Text("Carnet") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Materia") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = term,
                    onValueChange = { term = it },
                    label = { Text("Ciclo académico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Evaluaciones",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))

                NoteTextField(note = note1, onNoteChange = { note1 = it }, label = "Nota 1")
                Spacer(modifier = Modifier.height(8.dp))
                NoteTextField(note = note2, onNoteChange = { note2 = it }, label = "Nota 2")
                Spacer(modifier = Modifier.height(8.dp))
                NoteTextField(note = note3, onNoteChange = { note3 = it }, label = "Nota 3")

                errorMessage?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val n1 = note1.toFloatOrNull()
                        val n2 = note2.toFloatOrNull()
                        val n3 = note3.toFloatOrNull()

                        // Validamos que todos los datos académicos estén completos y que las notas sean numéricas.
                        errorMessage = when {
                            studentName.isBlank() || carnet.isBlank() || subject.isBlank() || term.isBlank() ||
                                note1.isBlank() || note2.isBlank() || note3.isBlank() -> {
                                "Por favor completa todos los campos del formulario."
                            }
                            n1 == null || n2 == null || n3 == null -> {
                                "Las tres notas deben ingresarse como valores numéricos."
                            }
                            else -> null
                        }

                        if (errorMessage == null && n1 != null && n2 != null && n3 != null) {
                            // Calculamos el promedio simple con las tres evaluaciones registradas.
                            val average = (n1 + n2 + n3) / 3f
                            val status = if (average >= 6f) "Aprobado" else "Reprobado"
                            onCalculate(studentName, carnet, subject, term, n1, n2, n3, average, status)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Generar resultado académico")
                }
            }
        }
    }
}

@Composable
private fun NoteTextField(
    note: String,
    onNoteChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = note,
        onValueChange = onNoteChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}
