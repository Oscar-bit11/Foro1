# NotasComposeApp

Aplicación Android creada con **Kotlin + Jetpack Compose + Material 3** para simular un flujo académico simple:
- Inicio de sesión.
- Registro de usuario.
- Ingreso de nombre del estudiante y tres notas.
- Cálculo automático del promedio.
- Resultado final con estado **Aprobado** o **Reprobado**.

## ¿Qué hace la aplicación?

La app permite practicar navegación entre pantallas y validación de formularios en Compose:
1. **LoginScreen**: captura usuario y contraseña.
2. **RegisterScreen**: permite crear cuenta y volver al login.
3. **HomeScreen**: ingresa estudiante y tres notas.
4. **ResultScreen**: muestra notas, promedio y estado.

Reglas implementadas:
- Si el promedio es **mayor o igual a 6**, estado: **Aprobado**.
- Si el promedio es **menor a 6**, estado: **Reprobado**.
- Se valida que no haya campos vacíos.
- Se valida que las notas sean numéricas.

## Tecnologías usadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **Android Gradle Plugin + Gradle Kotlin DSL**

## Cómo ejecutar el proyecto

1. Abrir la carpeta del proyecto en **Android Studio**.
2. Esperar sincronización de Gradle.
3. Ejecutar en emulador o dispositivo físico (API 24+).

### Comando opcional por terminal

```bash
./gradlew assembleDebug
```

## Flujo de pantallas

- `LoginScreen`
  - Botón **Ingresar** → `HomeScreen`
  - Botón **Registrarse** → `RegisterScreen`
- `RegisterScreen`
  - Botón **Crear cuenta** (validando datos) → vuelve a `LoginScreen`
  - Botón **Volver al login** → `LoginScreen`
- `HomeScreen`
  - Botón **Calcular promedio** → `ResultScreen`
- `ResultScreen`
  - Botón **Ingresar nuevas notas** → `HomeScreen`

## Estructura principal

- `app/src/main/java/com/example/notascomposeapp/MainActivity.kt`
- `app/src/main/java/com/example/notascomposeapp/navigation/AppNavigation.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/LoginScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/RegisterScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/HomeScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/ResultScreen.kt`

---
Diseño orientado a una defensa universitaria: código simple, legible y fácil de explicar.
