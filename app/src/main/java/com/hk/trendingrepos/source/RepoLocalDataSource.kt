package com.hk.trendingrepos.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hk.trendingrepos.App
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.model.RepositoryDto
import com.hk.trendingrepos.model.RepositoryOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoLocalDataSource {
    suspend fun insertRepositories(repositories: List<Repository>) {
        withContext(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(App.instance.applicationContext)
            db.repositoryDao().insertRepositories(repositories)
            val owners = arrayListOf<RepositoryOwner>()
            for (repo in repositories) {
                val owner = repo.repoOwner
                owner.repositoryId = repo.repositoryId
                owners.add(owner)
            }
            db.repositoryDao().insertRepoOwners(owners)
        }
    }

    fun getAllRepositories(): LiveData<List<RepositoryDto>> {
        val db = AppDatabase.getDatabase(App.instance.applicationContext)
        return db.repositoryDao().getAllRepositories()
    }
}