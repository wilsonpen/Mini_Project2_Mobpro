package com.ziven0069.miniproject2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ziven0069.miniproject2.screen.DetailScreen
import com.ziven0069.miniproject2.screen.KEY_ID_BUKU
import com.ziven0069.miniproject2.screen.MainScreen
import com.ziven0069.miniproject2.screen.DetailScreen
import com.ziven0069.miniproject2.screen.KEY_ID_BUKU
import com.ziven0069.miniproject2.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable( route = Screen.Home.route){
            MainScreen(navController)
        }
        composable( route = Screen.FormBaru.route ) {
            DetailScreen(navController)
        }

        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU){
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_BUKU)
            DetailScreen(navController,id)
        }
    }
}