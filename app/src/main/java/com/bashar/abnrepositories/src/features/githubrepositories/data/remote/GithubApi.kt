package com.bashar.abnrepositories.src.features.githubrepositories.data.remote

import com.bashar.abnrepositories.src.features.githubrepositories.data.model.RepoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("users/abnamrocoesd/repos")
    suspend fun getRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<RepoDto>
}