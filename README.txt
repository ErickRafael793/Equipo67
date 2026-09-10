Equipo 67 - US11 y US12

Este ZIP contiene dos implementaciones mínimas en Kotlin + Jetpack Compose:

US11
- Historia: Listar todos los usuarios registrados.
- Archivo: US11/MainActivity.kt
- Función: muestra una lista simple de usuarios con ID, nombre y correo.

US12
- Historia: Visualizar histórico de carritos globales.
- Archivo: US12/MainActivity.kt
- Función: muestra una lista simple de carritos con ID, usuario, cantidad de productos y total.

IMPORTANTE
- Cada MainActivity.kt está pensado para trabajarse en una rama independiente.
- Ambos usan el package actual del repo: com.example.solucionbase
- Ambos reutilizan SolucionbaseTheme, que ya existe en el proyecto.
- No se agregaron dependencias nuevas.
- Los datos son locales y de ejemplo porque las historias originales no especifican aquí una API concreta.
- Cuando exista fuente de datos real, únicamente se reemplazan las listas locales.

Ramas sugeridas:
US11-Listar-todos-los-usuarios-registrados
US12-Historico-global-de-carritos

Commits sugeridos:
feat: implement US11 user list
feat: implement US12 global cart history
