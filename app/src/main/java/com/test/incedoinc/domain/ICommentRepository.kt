package com.test.incedoinc.domain

import com.test.incedoinc.domain.CommentEntity

interface ICommentRepository {
    suspend fun getComments(): List<CommentEntity>
}