package com.bashar.abnrepositories.src.features.githubrepositories.domain.model

import android.net.Uri
import com.google.gson.Gson

data class Repo(
    val id: Long,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val visibility: String,
    val description: String?,
    val htmlUrl: String,
    val ownerAvatar: String?,
    val ownerName: String?
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}