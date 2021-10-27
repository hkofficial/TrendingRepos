package com.hk.trendingrepos.model

import androidx.room.Embedded
import androidx.room.Relation

data class RepositoryDto(
    @Embedded
    val repository: Repository,
    @Relation(
        parentColumn = "id",
        entityColumn = "repository_id"
    )
    val owner: RepositoryOwner
)