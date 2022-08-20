package com.learning.journey.home.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.learning.journey.R
import com.learning.journey.base.core.ui.BaseAppCompatActivity
import com.learning.journey.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseAppCompatActivity() {

    companion object {
        const val TAG = "HomeActivity"
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        activity = this
    }

}