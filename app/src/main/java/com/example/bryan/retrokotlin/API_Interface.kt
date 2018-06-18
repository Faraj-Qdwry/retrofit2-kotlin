package com.example.bryan.retrokotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface API_Interface {

    //Here, we specify the api extension.
    @GET("users")
    fun getUserDetails(): Call<ArrayList<User>>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<ArrayList<Repo>>

    //Definition of CALL on Retrofit2
    /*
    Call<T>
    T - Successful response body type. (Object to map to upon successful request.)

    An invocation of a Retrofit method that sends a request to a webserver and returns a response.
    Each call yields its own HTTP request and response pair.
    Use clone() to make multiple calls with the same parameters to the same webserver; this may be
    used to implement polling or to retry a failed call.

    Sync: Calls may be executed synchronously with execute(),
    Async: or asynchronously with enqueue(retrofit2.Callback<T>).

    In either case the call can be canceled at any time with cancel(). A call that is busy writing its request or reading its response may receive a IOException; this is working as designed.
    */

    companion object Factory {
        fun create(): API_Interface = Retrofit.Builder().apply {
            baseUrl("https://api.github.com")
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(API_Interface::class.java)

    }


}