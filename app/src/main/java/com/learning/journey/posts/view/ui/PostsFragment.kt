package com.learning.journey.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.journey.R
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.databinding.FragmentPostsBinding
import com.learning.journey.posts.view.adapter.PostListAdapter
import com.learning.journey.posts.view.callback.PostListAdapterListener
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java]
    }

    private lateinit var binding: FragmentPostsBinding

    private var postList: MutableList<Post> = mutableListOf()

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postListAdapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initRecyclerView()
        fetchAllPosts()
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
            NavHostFragment.findNavController(this@PostsFragment)
                .navigate(R.id.action_navigation_post_to_navigation_post_detail, bundle)
        }

    }

    private fun fetchAllPosts() {
        postsViewModel.getAllPosts().observe(viewLifecycleOwner) {
            postList.clear()
            postList.addAll(it)
            postListAdapter.setPostList(postList)
        }
    }

}