package com.learning.journey

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.learning.journey.base.core.data.persistent.AppDatabase
import com.learning.journey.base.core.data.persistent.entities.Comment
import com.learning.journey.base.core.data.persistent.entities.Post
import com.learning.journey.posts.data.local.CommentDao
import com.learning.journey.posts.data.local.PostDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class JourneyDatabaseTest : TestCase() {
    // get reference to the LanguageDatabase and LanguageDao class
    private lateinit var db: AppDatabase
    private lateinit var postDao: PostDao
    private lateinit var commentDao: CommentDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postDao = db.postDao()
        commentDao = db.commentDao()
    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    fun closeDb() {
        db.close()
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun writeAndReadPost() {
        val postList = mutableListOf<Post>()
        val post = Post("99", "Title", "Description", "")
        val post2 = Post("100", "Title", "Description", "")
        postList.add(post)
        runBlocking {
            postDao.insertAll(postList)
        }
        postDao.getAllPosts().observeForever {
            assertThat(it.contains(post)).isTrue()
        }
    }

    @Test
    fun writeAndReadComment() {
        val commentList = mutableListOf<Comment>()
        val comment = Comment("100", "comment body", "1")
        commentList.add(comment)
        runBlocking {
            commentDao.insertAll(commentList)
        }
        commentDao.getAllCommentsForPostId("1").observeForever {
            assertThat(it.contains(comment)).isTrue()
        }
    }

}