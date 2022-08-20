package com.learning.journey.posts.view.callback

import com.learning.journey.base.core.data.persistent.entities.Post

interface PostListAdapterListener {
    fun onPostItemClick(post: Post?)
}