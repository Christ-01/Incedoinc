package com.test.incedoinc.data

import com.test.incedoinc.domain.CommentEntity

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    fun toEntity(): CommentEntity {
        return CommentEntity(
            postId,
            id,
            name,
            email,
            null,
            body
        )
    }
}
