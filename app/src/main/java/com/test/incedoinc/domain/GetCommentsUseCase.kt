package com.test.incedoinc.domain

import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val postRepository: ICommentRepository
) {
    suspend operator fun invoke(): List<CommentEntity> {
        return postRepository.getComments()
    }
}