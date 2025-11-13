package com.bashar.abnrepositories.src.core.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bashar.abnrepositories.src.core.navigation.utils.RepoNavType
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repodetails.RepoDetailsScreen
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist.RepoListScreen
import com.bashar.abnrepositories.src.features.setting.presentation.screens.SettingScreen
import com.bashar.abnrepositories.src.features.splash.presentation.SplashScreen
import androidx.core.net.toUri

val LocalNavController = compositionLocalOf<NavHostController>() {
//    navController
    throw IllegalStateException("NavController not provided")
}

@Composable
fun MyAppNavigator(
    navController: NavHostController = rememberNavController(),
    navigatorBottomNavigation: NavHostController,
    startDestination: String = Screen.SplashRoute.route,
) {

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = startDestination) {

            composable(Screen.SplashRoute.route) {
                SplashScreen(
                    onNavigateToScreen = {
                        navController.navigate(Screen.RepoListScreenRoute.route) {
                            popUpTo(Screen.SplashRoute.route) { inclusive = true }
                        }
                    })
            }

            composable(Screen.RepoListScreenRoute.route) {
                RepoListScreen(
                    onNavigateToDetail = { repo ->
                        navController.navigate(Screen.RepoDetailsScreen.sendRepo(repo))
                    },
                    onNavigateToSetting = {
                        navController.navigate(Screen.SettingScreenRoute.route)
                    })
            }

            composable(
                Screen.RepoDetailsScreen.route, arguments = listOf(
                    navArgument("repo") {
                        type = RepoNavType()

                    },
                )
            ) { navBackStackEntry ->
                RepoDetailsScreen(
                    onBack = { navController.popBackStack() },
                    onOpenInBrowser = {
                        val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                        navController.context.startActivity(intent)
                    }
                )
            }

            composable(Screen.SettingScreenRoute.route) {
                SettingScreen(onBack = {
                    navController.popBackStack()
                })
            }

        }
    }
    // Add a listener to detect route not found
    // Add a custom navigation event listener
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
