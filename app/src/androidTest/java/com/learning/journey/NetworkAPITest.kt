package com.learning.journey

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.GsonBuilder
import com.learning.journey.base.core.constant.AppConstants
import com.learning.journey.content.data.remote.service.ContentAPIService
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class NetworkAPITest : TestCase() {

    private lateinit var retrofit: Retrofit
    private lateinit var contentAPIService: ContentAPIService

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application

        val gson = GsonBuilder().create()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        contentAPIService = retrofit.create(ContentAPIService::class.java)
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun contentAPITest() {
        runBlocking {
            val isSuccess = contentAPIService.fetchContent().isSuccessful
            assertTrue(isSuccess)
        }
    }

}