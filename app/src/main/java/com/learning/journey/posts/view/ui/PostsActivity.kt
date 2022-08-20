package com.learning.journey.posts.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.learning.journey.R
import com.learning.journey.base.core.ui.BaseAppCompatActivity
import com.learning.journey.databinding.ActivityPostsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsActivity : BaseAppCompatActivity() {

    companion object {
        const val TAG = "PostsActivity"
    }

    private lateinit var binding: ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        activity = this
    }

}