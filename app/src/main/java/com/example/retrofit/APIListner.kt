package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET




public interface APIListner {

    @GET("posts")
    fun getPosts(): Call<List<User>>
}