package com.bashar.abnrepositories.src.core.di.hilt.moduls

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.bashar.abnrepositories.src.core.data.local.room.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext ctx: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = { ctx.preferencesDataStoreFile("preferences_pb") }
        )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): RepoDatabase =
        Room.databaseBuilder(ctx, RepoDatabase::class.java, "repos.db").build()

    @Provides
    fun provideRepoDao(db: RepoDatabase) = db.repoDao()


}
