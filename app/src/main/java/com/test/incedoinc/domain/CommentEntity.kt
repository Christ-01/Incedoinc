package com.test.incedoinc.domain

import android.net.Uri

data class CommentEntity(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val imageUri: Uri?,
    val body: String
)
