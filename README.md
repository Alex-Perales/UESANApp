# UESANApp

Aplicación Android desarrollada con **Jetpack Compose** para explorar países clasificados en la FIFA 2026, con autenticación de usuarios y sistema de favoritos persistente.

## Características

- **Autenticación** con Firebase (registro e inicio de sesión)
- **Pantalla de inicio** con lista de 10 países sudamericanos clasificados al Mundial FIFA 2026, incluyendo bandera y ranking
- **Sistema de favoritos** persistente con Room Database (agregar/eliminar países)
- **Navegación lateral** mediante un Drawer con acceso a Home, Permisos y Favoritos
- **Tema Material 3** con soporte para colores dinámicos en Android 12+

## Tecnologías

| Tecnología | Uso |
|---|---|
| Jetpack Compose | UI declarativa |
| Firebase Auth | Registro e inicio de sesión |
| Firebase Firestore | Almacenamiento de datos de usuario |
| Room Database | Favoritos locales persistentes |
| Navigation Compose | Navegación entre pantallas |
| Coil | Carga de imágenes (banderas) |
| Kotlin Coroutines | Operaciones asíncronas |
| Material 3 | Diseño y componentes UI |

## Estructura del proyecto

```
app/src/main/java/com/tunalex/uesanapp/
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt       # Base de datos Room
│   │   ├── CountryDao.kt        # Operaciones CRUD de favoritos
│   │   └── CountryEntity.kt     # Entidad Room (nombre, ranking, imagen)
│   ├── model/
│   │   └── CountryModel.kt      # Modelo de datos de país
│   └── remote/
│       └── FirebaseAuthManager.kt  # Autenticación Firebase
├── presentacion/
│   ├── auth/
│   │   ├── LoginScreen.kt       # Pantalla de inicio de sesión
│   │   └── RegisterScreen.kt    # Pantalla de registro
│   ├── favorites/
│   │   ├── FavoritesScreen.kt   # Lista de favoritos guardados
│   │   └── FavoritesViewModel.kt # Lógica de favoritos con StateFlow
│   ├── home/
│   │   └── HomeScreen.kt        # Lista de países con ranking
│   └── navigation/
│       ├── AppNavGraph.kt       # Grafo de navegación
│       └── DrawerScaffold.kt    # Scaffold con Navigation Drawer
├── ui/theme/                    # Tema, colores y tipografía
└── MainActivity.kt
```

## Requisitos

- Android Studio Hedgehog o superior
- SDK mínimo: Android 7.0 (API 24)
- SDK objetivo: Android 15 (API 35)
- Archivo `google-services.json` en `/app` (Firebase)

## Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Alex-Perales/UESANApp.git
   ```
2. Abre el proyecto en Android Studio.
3. Agrega tu archivo `google-services.json` dentro de la carpeta `app/` (obtenido desde la consola de Firebase).
4. Sincroniza Gradle y ejecuta la app en un emulador o dispositivo físico.

## Pantallas

| Pantalla | Descripción |
|---|---|
| Registro | Formulario con nombre, correo y contraseña |
| Login | Acceso con correo y contraseña |
| Home | Lista de 10 países FIFA 2026 con botón de favorito |
| Favoritos | Lista de países guardados como favoritos |

## Países incluidos

Colombia, Argentina, Chile, Perú, Ecuador, Brasil, Uruguay, Paraguay, Bolivia y Venezuela — clasificados al Mundial FIFA 2026.
