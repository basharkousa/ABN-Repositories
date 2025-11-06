package com.bashar.abnrepositories.src.features.githubrepositories.data.model

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val visibility: String?,
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    val owner: OwnerDto
)

data class OwnerDto(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val login: String?
)