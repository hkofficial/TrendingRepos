package com.hk.trendingrepos.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.source.RepoRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityPresenter() {
    private val repositoryList: MutableLiveData<ArrayList<Repository>> by lazy { MutableLiveData<ArrayList<Repository>>() }

    private var view: ViewCallBack? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    constructor(view: ViewCallBack) : this() {
        this.view = view
    }

    fun getRepositories(): LiveData<ArrayList<Repository>> {
        return repositoryList
    }

    fun getRepositoriesFromServer() {
        view?.showProgressBar()
        val repo = RepoRemoteSource()
        coroutineScope.launch {
            val response = repo.getRepositories()
            view?.hideProgressBar()
            if (response.isSuccessful()) {
                response.data?.let {
                    repositoryList.value = it
                }
            } else
                view?.onRepositoryFetchFailed()
        }
    }

    interface ViewCallBack {
        fun showProgressBar()
        fun hideProgressBar()
        fun onRepositoryFetchFailed()
    }
}