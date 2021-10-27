package com.hk.trendingrepos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Repository(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val repositoryId: Long,
    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    val repositoryName: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val repositoryDescription: String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: String?,
    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    val starCount: Int = 0
) {
    @Ignore
    @SerializedName("owner")
    lateinit var repoOwner: RepositoryOwner
}
