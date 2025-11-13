package com.bashar.abnrepositories.src.core.navigation

import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

sealed class Screen(val route: String) {

    data object SplashRoute : Screen("splashScreen")
    data object RepoListScreenRoute : Screen("RepoListScreen")
    data object SettingScreenRoute : Screen("settingScreen")

    data object RepoDetailsScreen : Screen("ReopDetailsScreen/{repo}"){
        fun sendRepo(repo: Repo) = "ReopDetailsScreen/${repo}"
    }
}