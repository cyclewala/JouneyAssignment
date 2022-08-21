package com.learning.journey.posts.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.databinding.FragmentSearchCommentsBinding
import com.learning.journey.posts.view.adapter.CommentListAdapter
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCommentsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java]
    }

    private lateinit var binding: FragmentSearchCommentsBinding
    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentListAdapter: CommentListAdapter

    private var commentList: MutableList<Comment> = mutableListOf()
    private var searchText: String = ""
    private var postId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_comments, container, false)
        init()
        return binding.root
    }

    private fun init() {
        getDataFromBundle()
        initRecyclerView()
        initToolbar()
        initTextWatcher()
    }

    private fun getDataFromBundle() {
        if (arguments != null && requireArguments().containsKey("postId")) {
            postId = requireArguments().getString("postId")
        }
    }

    private fun initRecyclerView() {
        if (isAdded) {
            commentList = arrayListOf()
            commentRecyclerView = binding.rvComments
            commentListAdapter = CommentListAdapter(commentList)
            commentRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            commentRecyclerView.addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            commentRecyclerView.adapter = commentListAdapter
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = binding.toolbarSearchComment
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this@SearchCommentsFragment).popBackStack()
        }
    }

    private fun initTextWatcher() {
        binding.edSearch.addTextChangedListener(textWatcher)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            searchText = s.toString().trim()
            if (searchText.isNotEmpty()) {
                callSearchCommentsApi(searchText)
            } else {
                commentList.clear()
                commentListAdapter.setCommentList(commentList)
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private fun callSearchCommentsApi(searchText: String) {
        postId?.let {
            val searchComments = postsViewModel.getAllCommentsForText(it, searchText)
            commentList.clear()
            commentList.addAll(searchComments)
            commentListAdapter.setCommentList(commentList)
        }

    }

}