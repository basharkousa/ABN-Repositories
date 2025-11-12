package com.bashar.abnrepositories.src.features.githubrepositories.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val visibility: String?,
    val description: String?,
    val htmlUrl: String,
    val ownerAvatar: String?,
    val ownerName: String?,
    val remoteIndex: Int
)

fun RepoEntity.toDomain() = Repo(
    id,
    name,
    fullName,
    isPrivate,
    visibility ?: if (isPrivate) "private" else "public",
    description,
    htmlUrl,
    ownerAvatar,
    ownerName
)

fun Repo.toEntity() = RepoEntity(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = isPrivate,
    visibility = visibility,
    description = description,
    htmlUrl = htmlUrl,
    ownerAvatar = ownerAvatar,
    ownerName = ownerName,
    remoteIndex = 0
)
