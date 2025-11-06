package com.bashar.abnrepositories

import androidx.multidex.MultiDexApplication
import com.bashar.abnrepositories.src.features.setting.domain.ApplyLanguageUseCase
import com.bashar.abnrepositories.src.features.setting.domain.GetLanguageFlowUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class HiltApplication() : MultiDexApplication(){
    @Inject lateinit var getLanguage: GetLanguageFlowUseCase
    @Inject lateinit var applyLanguage: ApplyLanguageUseCase

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            val lang = getLanguage().first()
            applyLanguage(lang)
        }
    }

}