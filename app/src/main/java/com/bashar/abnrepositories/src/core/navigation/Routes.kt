package com.bashar.abnrepositories.src.core.navigation

import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

sealed class Screen(val route: String) {

    data object SplashRoute : Screen("splashScreen")

    data object MainScreenRoute : Screen("mainScreen")
    data object RepoListScreenRoute : Screen("RepoListScreen")
    data object SettingScreenRoute : Screen("settingScreen")


/*    data object AlbumsScreen : Screen("albumsScreen/{artist}"){
        fun sendArtistName(name: String) = "albumsScreen/$name"
    }*/
    data object RepoDetailsScreen : Screen("ReopDetailsScreen/{repo}"){
        fun sendRepo(repo: Repo) = "ReopDetailsScreen/${repo}"
//        fun sendAlbum(album: String) = "albumsDetailsScreen/${album}"
//        fun sendTestModel(album: TestModel) = "albumsDetailsScreen/${album}"
    }
}