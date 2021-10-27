package com.hk.trendingrepos.presenter

import com.hk.trendingrepos.model.Repository


class MainActivityPresenter() {
    private var repositoryList: ArrayList<Repository>
    private var view: ViewCallBack? = null
    init {
        repositoryList = arrayListOf()
    }
    constructor(view: ViewCallBack): this() {
        this.repositoryList = arrayListOf()
        this.view = view
    }

    fun getRepositories(){
        view?.showProgressBar()
        repositoryList.add(
            Repository(
                userName = "hamza",
                repositoryName = "Trending Repos",
                repositoryDescription = "simple repo",
                userImage = "https://avatars.githubusercontent.com/u/4314092?v=4",
                language = "Kotlin",
                starCount = 44
            )
        )
        repositoryList.add(
            Repository(
                userName = "hamza",
                repositoryName = "Tellotalk",
                repositoryDescription = "simple repo",
                userImage = "https://avatars.githubusercontent.com/u/4314092?v=4",
                language = "Kotlin",
                starCount = 44
            )
        )
        repositoryList.add(
            Repository(
                userName = "hamza",
                repositoryName = "Ebravo",
                repositoryDescription = "simple repo",
                userImage = "https://avatars.githubusercontent.com/u/4314092?v=4",
                language = "Kotlin",
                starCount = 44
            )
        )
        repositoryList.add(
            Repository(
                userName = "hamza",
                repositoryName = "Trending Repos",
                repositoryDescription = "simple repo",
                userImage = "https://avatars.githubusercontent.com/u/4314092?v=4",
                language = "Kotlin",
                starCount = 44
            )
        )
        view?.hideProgressBar()
        view?.updateRepositoryList(repositoryList)
//        view?.onDataFetchFailed()
    }
    interface ViewCallBack {
        fun updateRepositoryList(list: ArrayList<Repository>)
        fun showProgressBar()
        fun hideProgressBar()
        fun onRepositoryFetchFailed()
    }
}