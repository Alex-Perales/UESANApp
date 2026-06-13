package com.tunalex.uesanapp.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tunalex.uesanapp.presentacion.auth.LoginScreen
import com.tunalex.uesanapp.presentacion.auth.RegisterScreen
import com.tunalex.uesanapp.presentacion.favorites.FavoritesScreen
import com.tunalex.uesanapp.presentacion.favorites.FavoritesViewModel
import com.tunalex.uesanapp.presentacion.home.HomeScreen
import androidx.compose.material3.Text

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen(favoritesViewModel)

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
                FavoritesScreen(favoritesViewModel)

                Text("Pantalla de Favoritos")
            }
        }
    }
}
