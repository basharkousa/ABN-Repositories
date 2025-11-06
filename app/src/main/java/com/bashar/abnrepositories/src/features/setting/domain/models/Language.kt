package com.bashar.abnrepositories.src.features.setting.domain.models

enum class Language(val tag: String) {
    System(""), English("en"), Dutch("nl");

    companion object {
        fun fromTag(tag: String?): Language =
            entries.firstOrNull { it.tag == (tag ?: "") } ?: System
    }
}
