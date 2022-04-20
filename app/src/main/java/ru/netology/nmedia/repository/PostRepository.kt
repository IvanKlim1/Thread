package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

//interface PostRepository {
//    fun getAll(): List<Post>
//    fun likeById(id: Long)
//    fun unlikeById(id: Long)
//    fun save(post: Post)
//    fun removeById(id: Long)
//}


interface PostRepository {

    fun getAllAsync(callback: PostCallback<List<Post>>)
    fun saveAsync(post: Post, callback: PostCallback<Post>)
    fun removeByIdAsync(id: Long, callback: PostCallback<Unit>)
    fun likeByIdAsync(id: Long, callback: PostCallback<Post>)
    fun unLikeByIdAsync(id: Long, callback: PostCallback<Post>)

    interface PostCallback<T> {
        fun onSuccess(value: T) {}
        fun onError(e: Exception) {}
    }
}
