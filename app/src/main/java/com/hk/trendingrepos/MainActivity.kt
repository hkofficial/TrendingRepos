package com.hk.trendingrepos

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hk.trendingrepos.databinding.ActivityMainBinding
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.presenter.MainActivityPresenter
import com.hk.trendingrepos.view.RepositoryAdapter

class MainActivity : AppCompatActivity(),MainActivityPresenter.ViewCallBack {
    private lateinit var adapter: RepositoryAdapter
    private lateinit var presenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding
    private val repositoryList = arrayListOf<Repository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        presenter = MainActivityPresenter(this)

        adapter = setRecyclerAdapter()

        presenter.getRepositories().observe(this, {
            repositoryList.clear()
            repositoryList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        presenter.getRepositoriesFromServer()
    }

    private fun setRecyclerAdapter(): RepositoryAdapter {
        binding.repositoryRv.layoutManager =
            LinearLayoutManager(this)
        val adapter = RepositoryAdapter(repositoryList)
        binding.repositoryRv.adapter = adapter
        return adapter
    }

    override fun showProgressBar() {
        binding.shimmerViewContainer.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
    }

    override fun onRepositoryFetchFailed() {
        binding.retryView.visibility = View.VISIBLE
        val retryImageView = findViewById<ImageView>(R.id.retry_image_view)
        Glide.with(this).load(R.drawable.retry_gif).into(retryImageView)
        binding.retryTv.setOnClickListener{
            presenter.getRepositories()
        }
    }
}