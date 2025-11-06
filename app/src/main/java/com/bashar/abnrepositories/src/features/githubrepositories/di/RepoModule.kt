package com.bashar.abnrepositories.src.features.githubrepositories.di

import com.bashar.abnrepositories.src.features.githubrepositories.data.RepoRepositoryImpl
import com.bashar.abnrepositories.src.features.githubrepositories.domain.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindRepoRepository(impl: RepoRepositoryImpl): RepoRepository
}
