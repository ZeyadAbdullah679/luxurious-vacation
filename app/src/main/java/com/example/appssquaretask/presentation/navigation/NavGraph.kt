package com.example.appssquaretask.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appssquaretask.data.model.city.citiesList
import com.example.appssquaretask.presentation.screens.cityDetails.CityDetails
import com.example.appssquaretask.presentation.screens.commingSoon.ComingSoonScreen
import com.example.appssquaretask.presentation.screens.login.LoginScreen
import com.example.appssquaretask.presentation.screens.popularRestaurant.PopularRestaurantsScreen
import com.example.appssquaretask.presentation.screens.signup.SignUpScreen
import com.example.appssquaretask.presentation.screens.start.StartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Start.name) {
        composable(route = Screens.Start.name) {
            StartScreen {
                navController.navigate(Screens.Login.name)
            }
        }
        composable(route = Screens.Signup.name) {
            SignUpScreen(
                onClickBack = { navController.navigateUp() },
                onLoginClick = {
                    navController.navigate(Screens.Login.name)
                },
            )
        }
        composable(
            route = Screens.Login.name
        ) {
            LoginScreen(
                navigateToSignUp = { navController.navigate(Screens.Signup.name) },
                navigateToHome = { navController.navigate(Screens.Home.name) }
            )
        }
        composable(route = Screens.Home.name) {
            PopularRestaurantsScreen(onRestaurantClick = { cityIndex ->
                navController.navigate("${Screens.Details.name}/$cityIndex")
            })
        }
        composable(
            route = "${Screens.Details.name}/{cityIndex}",
            arguments = listOf(navArgument("cityIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val cityIndex = backStackEntry.arguments?.getInt("cityIndex")
            val cityData = citiesList[cityIndex ?: 0]
            CityDetails(cityData = cityData, onBackClicked = { navController.navigateUp() })
        }
        composable(route = Screens.ComingSoon.name) {
            ComingSoonScreen(onRefreshClicked = {})
        }
    }
}