package com.learning.journey.base.core.utils

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.learning.journey.base.core.ui.BaseAppCompatActivity

/**
 * DataBindingActivity is an abstract class for providing [DataBindingUtil].
 * provides implementations of only [ViewDataBinding] from an abstract information.
 * Do not modify this class. This is a first-level abstraction class.
 * If you want to add more specifications, make another class which extends [DataBindingActivity].
 */
abstract class DataBindingActivity : BaseAppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int,
    ): Lazy<T> = lazy { DataBindingUtil.setContentView(this, resId) }
}
