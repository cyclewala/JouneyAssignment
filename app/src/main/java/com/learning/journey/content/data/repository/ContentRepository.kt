package com.learning.journey.content.data.repository

import com.learning.journey.base.core.data.Resource
import com.learning.journey.content.data.remote.datasource.ContentDataSource
import com.learning.journey.content.data.remote.response.ContentAPIResponse
import com.learning.journey.posts.data.local.CommentDao
import com.learning.journey.posts.data.local.PostDao
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val remoteDataSource: ContentDataSource,
    private val postDao: PostDao,
    private val commentDao: CommentDao,
) {

    suspend fun fetchContent(): Resource<ContentAPIResponse> {
        val response = remoteDataSource.fetchContent()
        if (response.status == Resource.Status.SUCCESS) {
            val it = response.data
            addDataToDb(it)
        }
        return response
    }

    private suspend fun addDataToDb(it: ContentAPIResponse?) {
        if (it?.posts?.isNotEmpty() == true) {
            postDao.insertAll(it.posts)
        }
        if (it?.comments?.isNotEmpty() == true) {
            commentDao.insertAll(it.comments)
        }
    }

    fun getAllPosts() = postDao.getAllPosts()

    fun getAllCommentsForPostId(postId: Int) = commentDao.getAllCommentsForPostId(postId)

}