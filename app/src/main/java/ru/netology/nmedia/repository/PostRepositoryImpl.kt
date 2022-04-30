package ru.netology.nmedia.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Response
import ru.netology.nmedia.api.PostsApi
import ru.netology.nmedia.dto.Post
import java.util.concurrent.TimeUnit


class PostRepositoryImpl: PostRepository {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<List<Post>>() {}

    companion object {
        private const val BASE_URL = "http://10.0.2.2:9999"
        private val jsonType = "application/json".toMediaType()
    }

    override fun getAllAsync(callback: PostRepository.PostsCallback<List<Post>>) {
        PostsApi.retrofitService.getAll().enqueue(object : retrofit2.Callback<List<Post>>{

                        override fun onResponse(call: Call<List<Post>>,
                            response: Response<List<Post>>
                        ) {
                            if (!response.isSuccessful) {
                                callback.onError(RuntimeException(response.message()))
                                return
                            }

                            callback.onSuccess(
                                response.body() ?: throw RuntimeException("body is null")
                            )
                        }

                        override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                            callback.onError(java.lang.RuntimeException(t))
                        }

                    })
            }



//    override fun getAllAsync(callback: PostRepository.PostCallback<List<Post>>) {
//        val request: Request = Request.Builder()
//            .url("${BASE_URL}/api/posts")
//            .build()
//
//        client.newCall(request)
//            .enqueue(object : Callback {
//                override fun onResponse(call: Call, response: Response) {
//                    val body = response.body?.string() ?: throw RuntimeException("body is null")
//                    try {
//                        callback.onSuccess(gson.fromJson(body, typeToken.type))
//                    } catch (e: Exception) {
//                        callback.onError(e)
//                    }
//                }
//
//                override fun onFailure(call: Call, e: IOException) {
//                    callback.onError(e)
//                }
//            })
//    }

    override fun likeByIdAsync(id: Long, callback: PostRepository.PostCallback<Post>) {
        PostsApi.retrofitService.likeById(id).enqueue(object : retrofit2.Callback<Post>{
                        override fun onResponse(
                            call: Call<Post>, response: Response<Post>
                        ) {
                            if (!response.isSuccessful) {
                                callback.onError(RuntimeException(response.message()))
                                return
                            }

                            callback.onSuccess(
                                response.body() ?: throw RuntimeException("body is null")
                            )
                        }

                        override fun onFailure(call: Call<Post>, t: Throwable) {
                            callback.onError(java.lang.RuntimeException(t))
                        }

                    })
            }


//    override fun likeByIdAsync(id: Long, callback: PostRepository.PostCallback<Post>) {
//        val request: Request = Request.Builder()
//            .post("".toRequestBody())
//            .url("${BASE_URL}/api/posts/$id/likes")
//            .build()
//
//        client.newCall(request)
//            .enqueue(
//                object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        callback.onError(e)
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        val body = response.body?.string() ?: throw RuntimeException("body is null")
//                        try {
//                            callback.onSuccess(
//                                gson.fromJson(
//                                    body,
//                                    Post::class.java
//                                )
//                            )
//                        } catch (e: Exception) {
//                            callback.onError(e)
//                        }
//                    }
//                })
//    }

    override fun unLikeByIdAsync(id: Long, callback: PostRepository.PostCallback<Post>) {
        PostsApi.retrofitService.dislikeById(id).enqueue(object : retrofit2.Callback<Post> {
                        override fun onResponse(
                            call: Call<Post>,
                            response: Response<Post>
                        ) {
                            if (!response.isSuccessful) {
                                callback.onError(RuntimeException(response.message()))
                                return
                            }

                            callback.onSuccess(
                                response.body() ?: throw RuntimeException("body is null")
                            )
                        }

                        override fun onFailure(call: Call<Post>, t: Throwable) {
                            callback.onError(java.lang.RuntimeException(t))
                        }

                    })
            }


    //    override fun unLikeByIdAsync(id: Long, callback: PostRepository.PostCallback<Post>) {
//        val request: Request = Request.Builder()
//            .delete()
//            .url("${BASE_URL}/api/posts/$id/likes")
//            .build()
//
//        client.newCall(request)
//            .enqueue(
//                object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        callback.onError(e)
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        val body = response.body?.string() ?: throw RuntimeException("body is null")
//                        try {
//                            callback.onSuccess(
//                                gson.fromJson(
//                                    body,
//                                    Post::class.java
//                                )
//                            )
//                        } catch (e: Exception) {
//                            callback.onError(e)
//                        }
//                    }
//                })
//
//    }
    override fun saveAsync(post: Post, callback: PostRepository.PostCallback<Post>) {
        PostsApi.retrofitService.save(post).enqueue(object : retrofit2.Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                            if (!response.isSuccessful) {
                                callback.onError(RuntimeException(response.message()))
                                return
                            }

                            callback.onSuccess(
                                response.body() ?: throw RuntimeException("body is null")
                            )
                        }

                        override fun onFailure(call: Call<Post>, t: Throwable) {
                            callback.onError(java.lang.RuntimeException(t))
                        }

            })
            }



    //    override fun saveAsync(post: Post, callback: PostRepository.PostCallback<Post>) {
//        val request: Request = Request.Builder()
//            .post(gson.toJson(post).toRequestBody(jsonType))
//            .url("${BASE_URL}/api/posts")
//            .build()
//
//        client.newCall(request)
//            .enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    callback.onError(e)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    val body = response.body?.string() ?: throw RuntimeException("body is null")
//                    try {
//                        callback.onSuccess(gson.fromJson(body, Post::class.java))
//                    } catch (e: Exception) {
//                        callback.onError(e)
//                    }
//                }
//            })
//    }
    override fun removeByIdAsync(id: Long, callback: PostRepository.PostCallbackUnit<Unit>) {
       PostsApi.retrofitService.removeById(id).enqueue(object : retrofit2.Callback<Unit> {
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if (!response.isSuccessful) {
                                callback.onError(RuntimeException(response.message()))
                                return
                            }

                            callback.onSuccess()
                        }

           override fun onFailure(call: Call<Unit>, t: Throwable) {
               callback.onError(java.lang.RuntimeException(t))
           }


                    })
            }
//    override fun removeByIdAsync(id: Long, callback: PostRepository.PostCallback<Unit>) {
//        val request: Request = Request.Builder()
//            .delete()
//            .url("${BASE_URL}/api/posts/$id")
//            .build()
//
//        client.newCall(request)
//            .enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    callback.onError(e)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    try {
//                        callback.onSuccess(Unit)
//                    } catch (e: Exception) {
//                        callback.onError(e)
//                    }
//                }
//            })
//    }
//}
}