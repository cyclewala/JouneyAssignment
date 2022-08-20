package com.learning.journey.posts.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learning.journey.base.core.data.persistent.entities.Comment

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(commentList: List<Comment>)

    @Query("SELECT * from comment where postId = :postId")
    fun getAllCommentsForPostId(postId: String): LiveData<List<Comment>>

}