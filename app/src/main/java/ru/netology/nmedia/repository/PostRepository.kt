package ru.netology.nmedia.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.nmedia.dto.Post


interface PostRepository {

    val data: Flow<List<Post>>
    fun getNewerCount(id: Long): Flow<Int>
    suspend fun getAllAsync()
    suspend fun saveAsync(post: Post)
    suspend fun removeByIdAsync(id: Long)
    suspend fun likeByIdAsync(id: Long)
    suspend fun unLikeByIdAsync(id: Long)
    suspend fun newerPostsViewed()

}
