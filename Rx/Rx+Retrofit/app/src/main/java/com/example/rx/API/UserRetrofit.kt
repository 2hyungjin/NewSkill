package com.example.rx.API

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserRetrofit {
    @GET("users")
    fun getUsers():Observable<User>

    companion object{
        fun getAPI(): UserRetrofit {
            val retrofit= Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserRetrofit::class.java)
        }
    }
}