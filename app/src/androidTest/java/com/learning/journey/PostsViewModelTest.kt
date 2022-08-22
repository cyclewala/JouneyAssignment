package com.learning.journey

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.content.data.repository.ContentRepository
import com.learning.journey.posts.viewmodel.PostsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class PostsViewModelTest : TestCase() {
    private lateinit var postsViewModel: PostsViewModel

    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @Inject
    lateinit var contentRepository: ContentRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public override fun setUp() {
        hiltrule.inject()
        postsViewModel = PostsViewModel(contentRepository)
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun getAllPostsTest() {
        val post = Post("1",
            "U.S., Taiwan to start formal trade talks under new initiative - Reuters",
            "The United States and Taiwan on Wednesday agreed to start trade talks under a new initiative, saying they wanted to reach agreements with \"economically meaningful outcomes\", in another sign of stepped up U.S. support for the island.",
            "https://www.reuters.com/resizer/tXp6pvnIAjbS5lPFA8hc41QMKv4=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/MYKLBAJVKNILJMUEZX6RAOWZMM.jpg")
        postsViewModel.getAllPosts().observeForever {
            val filteredPost = it.filter { it2 -> it2.title == post.title }
            assertThat(filteredPost.isNotEmpty()).isTrue()
        }
    }

    @Test
    fun getAllPostsSizeTest() {
        postsViewModel.getAllPosts().observeForever {
            assertThat(it.size == 5).isTrue()
        }
    }

    @Test
    fun getAllPostsForTextTest() {
        val post = Post("1",
            "U.S., Taiwan to start formal trade talks under new initiative - Reuters",
            "The United States and Taiwan on Wednesday agreed to start trade talks under a new initiative, saying they wanted to reach agreements with \"economically meaningful outcomes\", in another sign of stepped up U.S. support for the island.",
            "https://www.reuters.com/resizer/tXp6pvnIAjbS5lPFA8hc41QMKv4=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/MYKLBAJVKNILJMUEZX6RAOWZMM.jpg")
        val posts = postsViewModel.getAllPostsForText("Taiwan")
        val filteredPost = posts.filter { it -> it.title == post.title }
        assertThat(filteredPost.isNotEmpty()).isTrue()
    }

    @Test
    fun getAllCommentsForPostIdTest() {
        postsViewModel.getAllCommentsForPostId("1").observeForever {
            assertThat(it.size == 3).isTrue()
        }
    }

    @Test
    fun getAllCommentsForTextTest() {
        val comments = postsViewModel.getAllCommentsForText("1", "comment 1")
        assertThat(comments.size == 1).isTrue()
    }

}