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

## Estructura principal

- `app/src/main/java/com/example/notascomposeapp/MainActivity.kt`
- `app/src/main/java/com/example/notascomposeapp/navigation/AppNavigation.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/LoginScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/RegisterScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/HomeScreen.kt`
- `app/src/main/java/com/example/notascomposeapp/screens/ResultScreen.kt`
