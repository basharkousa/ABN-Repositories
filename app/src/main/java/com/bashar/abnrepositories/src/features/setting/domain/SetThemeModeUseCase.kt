package com.bashar.abnrepositories.src.features.setting.domain

import com.bashar.abnrepositories.src.features.setting.domain.models.ThemeMode
import com.bashar.abnrepositories.src.features.setting.domain.repositories.ISettingRepo


class SetThemeModeUseCase(private val repo: ISettingRepo) {
    suspend operator fun invoke(mode: ThemeMode) = repo.setTheme(mode)
}
