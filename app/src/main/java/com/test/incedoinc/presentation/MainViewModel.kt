package com.test.incedoinc.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.incedoinc.domain.GetCommentsUseCase
import com.test.incedoinc.domain.CommentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ViewResult{
    object ShowLoading: ViewResult()
    data class PostData(val comments: List<CommentEntity>): ViewResult()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {
    private var _fetchResult = MutableLiveData<ViewResult>()
    val fetchResult: LiveData<ViewResult>
        get() = _fetchResult

    fun getPosts() {
        _fetchResult.postValue(ViewResult.ShowLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val posts = getCommentsUseCase()
            _fetchResult.postValue(ViewResult.PostData(posts))
        }
    }
}