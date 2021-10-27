package com.hk.trendingrepos.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk.trendingrepos.databinding.RepositoryItemBinding
import com.hk.trendingrepos.model.RepositoryDto

class RepositoryAdapter(private val repositoryList: ArrayList<RepositoryDto> = arrayListOf()) :
    RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RepositoryViewHolder(
            RepositoryItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

}