package com.hk.trendingrepos.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hk.trendingrepos.databinding.RepositoryItemBinding
import com.hk.trendingrepos.model.RepositoryDto

class RepositoryViewHolder(
    private val binding: RepositoryItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(repository: RepositoryDto) {

        binding.repository = repository

        Glide.with(binding.root.context).load(repository.owner.userImage).into(binding.userImage)

    }
}