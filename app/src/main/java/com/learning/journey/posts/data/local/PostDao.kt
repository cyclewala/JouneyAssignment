package com.learning.journey.posts.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learning.journey.base.core.data.persistent.entities.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(postList: List<Post>)

    @Query("SELECT * from post")
    fun getAllPosts(): LiveData<List<Post>>

    @Query("SELECT * from post where (title LIKE '%' || :searchText || '%') || (description LIKE '%' || :searchText || '%')")
    fun getAllPostsForText(searchText: String): List<Post>

}