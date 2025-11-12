package com.bashar.abnrepositories.src.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoDao
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoEntity
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoRemoteKeys
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoRemoteKeysDao

@Database(
    entities = [RepoEntity::class, RepoRemoteKeys::class],
    version = 1,
    )
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun repoRemoteKeysDao(): RepoRemoteKeysDao
}
