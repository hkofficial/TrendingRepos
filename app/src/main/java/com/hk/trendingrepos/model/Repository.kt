package com.hk.trendingrepos.model

data class Repository(
    val userName: String,
    val repositoryName: String,
    val repositoryDescription: String,
    val userImage: String,
    val language: String,
    val starCount: Int)
