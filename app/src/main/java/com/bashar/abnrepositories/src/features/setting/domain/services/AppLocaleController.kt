package com.bashar.abnrepositories.src.features.setting.domain.services

import com.bashar.abnrepositories.src.features.setting.domain.models.Language

/** Platform port to apply the chosen app language. */
interface AppLocaleController {
    fun apply(language: Language)
}
