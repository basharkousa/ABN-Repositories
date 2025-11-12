package com.bashar.abnrepositories.src.features.githubrepositories.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RepoRemoteKeys(
    @PrimaryKey
    val repoId: String, // We use a dummy ID since we only have one list
    val nextKey: Int?
)