package com.bashar.abnrepositories.src.features.githubrepositories.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RepoRemoteKeys(
    @PrimaryKey
    val repoId: String,
    val nextKey: Int?
)