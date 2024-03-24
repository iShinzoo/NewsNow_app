package com.example.newsnow.remote

import com.example.newsnow.remote.dto.NewsResponse
import com.example.newsnow.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(

        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY

    ): NewsResponse

    @GET("everything")
    suspend fun SearchNews(

        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY

    ): NewsResponse

}