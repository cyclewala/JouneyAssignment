package com.learning.journey.posts.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.learning.journey.R
import com.learning.journey.base.core.ui.BaseAppCompatActivity
import com.learning.journey.databinding.ActivityPostsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsActivity : BaseAppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        updateStatusBarColor()
    }

    private fun updateStatusBarColor() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)
    }

}