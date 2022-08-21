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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.databinding.FragmentSearchPostsBinding
import com.learning.journey.posts.view.adapter.PostListAdapter
import com.learning.journey.posts.view.callback.PostListAdapterListener
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java]
    }

    private lateinit var binding: FragmentSearchPostsBinding
    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postListAdapter: PostListAdapter

    private var postList: MutableList<Post> = mutableListOf()
    private var searchText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_posts, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initRecyclerView()
        initToolbar()
        initTextWatcher()
    }

    private fun initRecyclerView() {
        if (isAdded) {
            postList = arrayListOf()
            postRecyclerView = binding.rvPosts
            postListAdapter = PostListAdapter(postList, postAdapterListener)
            postRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            postRecyclerView.adapter = postListAdapter
        }
    }

    private val postAdapterListener: PostListAdapterListener = object : PostListAdapterListener {

        override fun onPostItemClick(post: Post?) {
            val bundle = Bundle()
            if (post != null) {
                bundle.putParcelable("post", post)
            }
            NavHostFragment.findNavController(this@SearchPostsFragment)
                .navigate(R.id.action_navigation_search_post_to_navigation_post_detail, bundle)
        }

    }

    private fun initToolbar() {
        val toolbar: Toolbar = binding.toolbarSearchPost
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this@SearchPostsFragment).popBackStack()
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
                callSearchPostsApi(searchText)
            } else {
                postList.clear()
                postListAdapter.setPostList(postList)
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private fun callSearchPostsApi(searchText: String) {
        val searchPosts = postsViewModel.getAllPostsForText(searchText)
        postList.clear()
        postList.addAll(searchPosts)
        postListAdapter.setPostList(postList)
    }

}