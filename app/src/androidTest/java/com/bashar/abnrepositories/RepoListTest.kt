package com.bashar.abnrepositories

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.bashar.abnrepositories.src.core.ui.theme.AbnRepositoriesTheme
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist.RepoListScreen
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist.RepoListState
import org.junit.Rule
import org.junit.Test

class RepoListTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loading_Is_Active() {
        testRule.setContent {
            AbnRepositoriesTheme {
                RepoListScreen({}, {},
                    state = RepoListState(isLoading = true),{})
            }
        }
        testRule.onNodeWithContentDescription("Repo List Loading").assertIsDisplayed()
    }

}