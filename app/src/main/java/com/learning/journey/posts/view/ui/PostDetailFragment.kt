package com.learning.journey.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.databinding.FragmentPostDetailBinding
import com.learning.journey.posts.view.adapter.CommentListAdapter
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java]
    }

    private lateinit var binding: FragmentPostDetailBinding

    private var post: Post? = null
    private var commentList: MutableList<Comment> = mutableListOf()

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentListAdapter: CommentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false)
        init()
        return binding.root
    }

    private fun init() {
        getDataFromBundle()
        initView()
        initRecyclerView()
        fetchAllComments()
    }

    private fun getDataFromBundle() {
        if (arguments != null && requireArguments().containsKey("post")) {
            post = requireArguments().getParcelable<Post>("post")
        }
    }

    private fun initView() {
        post?.let {
            binding.post = it
        }
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = binding.toolbarPostDetail
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this@PostDetailFragment).popBackStack()
        }
    }

    private fun initRecyclerView() {
        if (isAdded) {
            commentList = arrayListOf()
            commentRecyclerView = binding.rvComments
            commentListAdapter = CommentListAdapter(commentList)
            commentRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            commentRecyclerView.adapter = commentListAdapter
        }
    }

    private fun fetchAllComments() {
        post?.let {
            postsViewModel.getAllCommentsForPostId(it.id).observe(viewLifecycleOwner) {
                commentList.clear()
                commentList.addAll(it)
                commentListAdapter.setCommentList(commentList)
            }
        }
    }

}