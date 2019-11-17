package com.example.myapplication.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    companion object {
        val SERVER_HOST = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>
}