package com.learning.journey.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.content.data.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
) : ViewModel() {

    fun getAllPosts(): LiveData<List<Post>> {
        return contentRepository.getAllPosts()
    }

    fun getAllPostsForText(searchText: String): List<Post> {
        return contentRepository.getAllPostsForText(searchText)
    }

    fun getAllCommentsForPostId(postId: String): LiveData<List<Comment>> {
        return contentRepository.getAllCommentsForPostId(postId)
    }

    fun getAllCommentsForText(postId: String, searchText: String): List<Comment> {
        return contentRepository.getAllCommentsForText(postId, searchText)
    }

}