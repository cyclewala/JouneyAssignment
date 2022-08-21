package com.learning.journey.posts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.databinding.ListItemPostBinding
import com.learning.journey.posts.view.callback.PostListAdapterListener
import com.learning.journey.posts.view.callback.PostListItemCallback

class PostListAdapter(
    private var postList: MutableList<Post>,
    listener: PostListAdapterListener,
) :
    RecyclerView.Adapter<PostListAdapter.PostItemViewHolder>() {

    private val postListItemCallback: PostListItemCallback = PostListItemCallback(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val listItemPostBinding: ListItemPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_post, parent, false
        )
        listItemPostBinding.callback = postListItemCallback
        return PostItemViewHolder(listItemPostBinding)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post: Post = postList[position]
        holder.listItemPostBinding.post = post
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setPostList(list: MutableList<Post>) {
        postList = list
        notifyDataSetChanged()
    }

    class PostItemViewHolder(itemViewBinding: ListItemPostBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        var listItemPostBinding: ListItemPostBinding

        init {
            listItemPostBinding = itemViewBinding
        }
    }

}