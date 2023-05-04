package com.test.incedoinc.data

import com.test.incedoinc.domain.ICommentRepository
import com.test.incedoinc.data.api.IncedoincApi
import com.test.incedoinc.domain.CommentEntity
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val api: IncedoincApi
) : ICommentRepository {
    override suspend fun getComments(): List<CommentEntity> {
        return api.getComments().map {
            it.toEntity()
        }
    }
}