package com.learning.journey.posts.view.callback

import com.learning.journey.base.core.data.persistent.entities.Post

class PostListItemCallback(private val listener: PostListAdapterListener?) {

    fun onItemClicked(post: Post?) {
        listener?.onPostItemClick(post)
    }

}