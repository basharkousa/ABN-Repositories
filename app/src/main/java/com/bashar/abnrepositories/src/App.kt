package com.bashar.abnrepositories.src

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.bashar.abnrepositories.src.core.navigation.MyAppNavigator
import com.bashar.abnrepositories.src.core.navigation.Screen
import com.bashar.abnrepositories.src.core.navigation.currentRoute
import com.bashar.abnrepositories.src.features.setting.domain.models.ThemeMode
import com.bashar.abnrepositories.src.features.setting.presentation.screens.SettingsViewModel
import com.bashar.abnrepositories.src.core.ui.theme.AbnRepositoriesTheme


@Composable
fun MyApp(vm: SettingsViewModel = hiltViewModel(), content: @Composable () -> Unit = {}) {

    val navController = rememberNavController()
    val navigatorBottomNavigation = rememberNavController()


    val lastBackPressTime = remember { mutableStateOf(0L) }
    val context = LocalContext.current
    val activity = (LocalActivity.current)

    val settingState by vm.state.collectAsState()
    val dark = when (settingState.theme) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val language = settingState.language


    AbnRepositoriesTheme(
        darkTheme = dark,
        language  = language
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                MyAppNavigator(navController, navigatorBottomNavigation, modifier = Modifier)
                Surface {

                    BackHandler(enabled = (currentRoute(navController) === Screen.MainScreenRoute.route)) {

                        val currentTime = System.currentTimeMillis()
                        println("currentTime: $currentTime")
                        if (currentTime - lastBackPressTime.value < 2000) {
                            // Exit the app
                            activity?.finish()
                        } else {
                            // Update the time of the last back press and show a message to the user
                            lastBackPressTime.value = currentTime
                            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

    }
}
