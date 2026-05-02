package com.tunalex.uesanapp.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tunalex.uesanapp.presentacion.auth.LoginScreen
import com.tunalex.uesanapp.presentacion.auth.RegisterScreen
import com.tunalex.uesanapp.presentacion.home.HomeScreen
import androidx.compose.material3.Text

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }
        composable("permissions") {
            DrawerScaffold(navController) {
                Text("Pantalla de Permisos")
            }
        }
        composable("favorites") {
            DrawerScaffold(navController) {
                Text("Pantalla de Favoritos")
            }
        }
    }
}
