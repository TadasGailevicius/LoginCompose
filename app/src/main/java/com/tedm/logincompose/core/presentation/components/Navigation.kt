package com.tedm.logincompose.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tedm.logincompose.core.util.Screen
import com.tedm.logincompose.feature_auth.presentation.login.LoginScreen
import com.tedm.logincompose.feature_profile.presentation.profile.ProfileScreen
import com.tedm.logincompose.feature_splash.presentation.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF009ffd),
                        Color(0xFF2a2a72)
                    )
                )
            )
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = Screen.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = Screen.ProfileScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                onLogout = {
                    navController.navigate(route = Screen.LoginScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }

    }
}