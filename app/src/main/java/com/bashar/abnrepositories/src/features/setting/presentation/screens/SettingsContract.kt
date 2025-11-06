package com.bashar.abnrepositories.src.features.setting.presentation.screens

import com.bashar.abnrepositories.src.features.setting.domain.models.Language
import com.bashar.abnrepositories.src.features.setting.domain.models.ThemeMode

data class SettingState(
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val language: Language = Language.System,
    val biometricEnabled: Boolean = false // hook up later
)

sealed class SettingsEvent {

//    data class ShowSnackBar(val message: String): TemplateEvents()
    object OnBackPress : SettingsEvent()
    object Refresh : SettingsEvent()
    object OnOpenLanguagePicker : SettingsEvent()
    object OnOpenThemePicker : SettingsEvent()

}