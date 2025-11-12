package com.bashar.abnrepositories.src.features.githubrepositories.data.mapper

import com.bashar.abnrepositories.src.features.githubrepositories.data.remote.model.RepoDto
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo


fun RepoDto.toDomain() = Repo(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = private,
    visibility = visibility ?: if (private) "private" else "public",
    description = description,
    htmlUrl = htmlUrl,
    ownerAvatar = owner.avatarUrl,
    ownerName = owner.login,
)
