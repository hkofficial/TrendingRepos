package com.hk.trendingrepos.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.model.RepositoryDto
import com.hk.trendingrepos.model.RepositoryOwner

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repository")
    fun getAllRepositories(): LiveData<List<RepositoryDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repoList: List<Repository>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepoOwners(owners: List<RepositoryOwner>)

}