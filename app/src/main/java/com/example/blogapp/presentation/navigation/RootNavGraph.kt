package com.example.blogapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.blogapp.presentation.screens.home.HomeScreen
import com.example.blogapp.presentation.screens.profile_edit.ProfileEditSreen


@Composable
fun RootNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){

        authNavGraph(navController = navController)
        composable(route = Graph.HOME){
            HomeScreen()
        }
    }
}
