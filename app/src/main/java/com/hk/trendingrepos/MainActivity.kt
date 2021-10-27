package com.hk.trendingrepos

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hk.trendingrepos.databinding.ActivityMainBinding
import com.hk.trendingrepos.model.Repository
import com.hk.trendingrepos.view.RepositoryAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repositoryList = arrayListOf<Repository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val retryImageView = findViewById<ImageView>(R.id.retry_image_view)
        Glide.with(this).load(R.drawable.retry_gif).into(retryImageView)
        val adapter = setRecyclerAdapter()
    }

    private fun setRecyclerAdapter(): RepositoryAdapter {
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
        binding.repositoryRv.layoutManager =
            LinearLayoutManager(this)
        val adapter = RepositoryAdapter(repositoryList)
        binding.repositoryRv.adapter = adapter
        return adapter
    }
}