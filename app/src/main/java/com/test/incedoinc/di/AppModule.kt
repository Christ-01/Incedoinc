package com.test.incedoinc.di

import com.test.incedoinc.domain.ICommentRepository
import com.test.incedoinc.data.CommentRepository
import com.test.incedoinc.data.api.ApiService
import com.test.incedoinc.data.api.IncedoincApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideCommentRepository(): ICommentRepository {
        return CommentRepository(provideAPI())
    }

    @Provides
    fun provideAPI(): IncedoincApi {
        return ApiService.incedoincApi
    }
}