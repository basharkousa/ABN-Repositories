package com.bashar.abnrepositories

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest{

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_IsActive(){
        testRule.setContent {
//            val viewModel = RepoDetailsViewModel(SavedStateHandle(mapOf("repo" to Re)))
//            AbnRepositoriesTheme {
//                RepoDetailsScreen(onBack = {}, onOpenInBrowser ={},viewModel = viewModel)
//            }
        }
        testRule.onNodeWithContentDescription("Repo Details Loading").assertIsDisplayed()
    }

}