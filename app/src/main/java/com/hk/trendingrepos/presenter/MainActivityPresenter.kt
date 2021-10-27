package com.hk.trendingrepos.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.model.RepositoryDto
import com.hk.trendingrepos.source.RepoLocalDataSource
import com.hk.trendingrepos.source.RepoRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityPresenter() {
    private lateinit var repositoryList: LiveData<List<RepositoryDto>>

    private var view: ViewCallBack? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    constructor(view: ViewCallBack) : this() {
        this.view = view
    }

    fun getRepositories(): LiveData<List<RepositoryDto>> {
        val repo = RepoLocalDataSource()
        repositoryList = repo.getAllRepositories()
        return repositoryList
    }

    fun getRepositoriesFromServer() {
        view?.showProgressBar()
        val repo = RepoRemoteSource()
        coroutineScope.launch {
            val response = repo.getRepositories()
            view?.hideProgressBar()
            if (!response.isSuccessful())
                view?.onRepositoryFetchFailed()
        }
    }

    interface ViewCallBack {
        fun showProgressBar()
        fun hideProgressBar()
        fun onRepositoryFetchFailed()
    }
}