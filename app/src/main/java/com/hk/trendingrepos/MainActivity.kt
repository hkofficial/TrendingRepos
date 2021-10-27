package com.hk.trendingrepos

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hk.trendingrepos.databinding.ActivityMainBinding
import com.hk.trendingrepos.model.RepositoryDto
import com.hk.trendingrepos.presenter.MainActivityPresenter
import com.hk.trendingrepos.view.RepositoryAdapter

class MainActivity : AppCompatActivity(), MainActivityPresenter.ViewCallBack {
    private lateinit var adapter: RepositoryAdapter
    private lateinit var presenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding
    private val repositoryList = arrayListOf<RepositoryDto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        presenter = MainActivityPresenter(this)

        adapter = setRecyclerAdapter()

        presenter.getRepositories().observe(this, {
            repositoryList.clear()
            repositoryList.addAll(it)
            adapter.notifyDataSetChanged()
            if (it.isNullOrEmpty())
                presenter.getRepositoriesFromServer()

        })

        binding.swipeRefresh.setOnRefreshListener {

            // This method performs the actual data-refresh operation.
            // The method calls setRefreshing(false) when it's finished.
            presenter.getRepositoriesFromServer()
        }

    }

    private fun setRecyclerAdapter(): RepositoryAdapter {
        binding.repositoryRv.layoutManager =
            LinearLayoutManager(this)
        val adapter = RepositoryAdapter(repositoryList)
        binding.repositoryRv.adapter = adapter
        return adapter
    }

    override fun showProgressBar() {
        binding.swipeRefresh.isRefreshing = true
        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.repositoryRv.visibility = View.GONE
        binding.retryView.visibility = View.GONE
    }

    override fun hideProgressBar() {
        binding.swipeRefresh.isRefreshing = false
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
        binding.retryView.visibility = View.GONE
        binding.repositoryRv.visibility = View.VISIBLE
    }

    override fun onRepositoryFetchFailed() {
        if(repositoryList.isNullOrEmpty()) {
            binding.retryView.visibility = View.VISIBLE
            val retryImageView = findViewById<ImageView>(R.id.retry_image_view)
            Glide.with(this).load(R.drawable.retry_gif).into(retryImageView)
            binding.retryButton.setOnClickListener {
                presenter.getRepositoriesFromServer()
            }
        }
    }
}