package com.example.myapplication.data

import retrofit2.Call
import retrofit2.http.*

interface SimpleApi {
    companion object {
        val SERVER_HOST = "https://jsonplaceholder.typicode.com"
    }

    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("/posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<List<Post>>

    @POST("/posts")
    fun createNewPost(@Body post: Post): Call<Post>
}