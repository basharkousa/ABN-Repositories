package com.bashar.abnrepositories.src.features.setting.domain

import com.bashar.abnrepositories.src.features.setting.domain.repositories.ISettingRepo


class GetLanguageFlowUseCase(private val repo: ISettingRepo) {
    operator fun invoke() = repo.observeLanguage()
}
