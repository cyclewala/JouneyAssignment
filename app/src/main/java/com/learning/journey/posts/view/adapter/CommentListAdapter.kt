package com.learning.journey.posts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.databinding.ListItemCommentBinding

class CommentListAdapter(
    private var commentList: MutableList<Comment>,
) :
    RecyclerView.Adapter<CommentListAdapter.CommentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        val listItemCommentBinding: ListItemCommentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_comment, parent, false
        )
        return CommentItemViewHolder(listItemCommentBinding)
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        val comment: Comment = commentList[position]
        holder.listItemCommentBinding.comment = comment
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun setCommentList(list: MutableList<Comment>) {
        commentList = list
        notifyDataSetChanged()
    }

    class CommentItemViewHolder(itemViewBinding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        var listItemCommentBinding: ListItemCommentBinding

        init {
            listItemCommentBinding = itemViewBinding
        }
    }

}