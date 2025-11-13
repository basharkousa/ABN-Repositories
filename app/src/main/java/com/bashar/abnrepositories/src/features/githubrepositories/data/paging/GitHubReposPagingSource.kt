package com.bashar.abnrepositories.src.features.githubrepositories.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bashar.abnrepositories.src.features.githubrepositories.data.mapper.toDomain
import com.bashar.abnrepositories.src.features.githubrepositories.data.remote.GitHubApi
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo


class GitHubReposPagingSource(
    private val api: GitHubApi,
    private val pageSize: Int = 5
) : PagingSource<Int, Repo>() {

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor)

        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> = try {

        val page = params.key ?: 1
        val items = api.getRepos(page = page, perPage = pageSize).map { it.toDomain() }

        LoadResult.Page(
            data = items,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (items.size < pageSize) null else page + 1
        )
    } catch (t: Throwable) {
        LoadResult.Error(t)
    }

}