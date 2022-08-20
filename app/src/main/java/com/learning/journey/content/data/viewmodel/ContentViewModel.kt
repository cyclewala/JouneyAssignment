package com.learning.journey.content.data.viewmodel

import androidx.lifecycle.ViewModel
import com.learning.journey.content.data.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
) : ViewModel() {

    fun sendContentLoadRequest() {
        GlobalScope.launch {
            contentRepository.fetchContent()
        }
    }

}