package com.learning.journey.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.learning.journey.R
import com.learning.journey.databinding.FragmentPostsBinding
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        init()
        return binding.root
    }

    private fun init() {
        fetchContent()
    }

    private fun fetchContent() {

    }

}