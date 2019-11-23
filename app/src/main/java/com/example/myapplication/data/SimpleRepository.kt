package com.example.myapplication.data

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SimpleRepository {
    private var api = Retrofit.Builder()
            .baseUrl(SimpleApi.SERVER_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpleApi::class.java)

//    fun getAllPosts() = api.getAllPosts().execute().body() ?: listOf()

    fun getAllPosts(result: (List<Post>) -> Unit) {
        api.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                result(response.body() ?: emptyList())
            }

        })
    }

    fun getPostById(id: Int, result: (Post) -> Unit) {
        api.getPostById(id).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                result(response.body()!!)
            }
        })
    }

    fun getPostsByUserId(userId: Int, result: (List<Post>) -> Unit) {
        api.getPostsByUserId(userId).enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                result(response.body() ?: emptyList())
            }

        })
    }

    fun createNewPost(post: Post, result: (Boolean) -> Unit) {
        api.createNewPost(post).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                result(response.body() != null)
            }

        })
    }
}