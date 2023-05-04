package com.test.incedoinc.data.api

import com.test.incedoinc.data.CommentResponse
import retrofit2.http.GET

interface IncedoincApi {
    @GET("posts/1/comments")
    suspend fun getComments(): List<CommentResponse>
}