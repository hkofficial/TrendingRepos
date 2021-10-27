package com.hk.trendingrepos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RepositoryOwner(
    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    var repositoryId: Long = 0,
    @ColumnInfo(name = "login")
    @SerializedName("login") val userName: String,
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url") val userImage: String
)
