package com.bashar.abnrepositories.src.core.navigation.utils

import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import com.google.gson.Gson

class RepoNavType : JsonNavType<Repo>() {

    override fun fromJsonParse(value: String): Repo = Gson().fromJson(value, Repo::class.java)

    override fun Repo.getJsonParse(): String = Gson().toJson(this)

}