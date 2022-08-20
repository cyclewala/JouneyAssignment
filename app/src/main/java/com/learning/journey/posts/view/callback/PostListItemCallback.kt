package com.learning.journey.posts.view.callback

import android.view.View
import com.learning.journey.base.core.data.persistent.entities.Post

class PostListItemCallback(private val listener: PostListAdapterListener?) {

    fun onItemClicked(view: View?, post: Post?) {
        listener?.onPostItemClick(post)
    }

}