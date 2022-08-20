package com.learning.journey.base.core.utils

import android.content.Context
import javax.inject.Inject

class BaseHeaderUtil @Inject constructor(context: Context) {

    private var context: Context? = null

    init {
        this.context = context
    }

    fun prepareHeaderMap(): MutableMap<String, String> {
        val headerMap = mutableMapOf<String, String>()

        headerMap["Content-Type"] = "application/json"
        return headerMap
    }


}