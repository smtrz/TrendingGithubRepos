package com.highbryds.trendingrepos.Interfaces


import com.highbryds.trendingrepos.Models.BaseTrending
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointsInterface {
    @GET("repositories")
    fun getNewsList(
        @Query("language") language: String,
        @Query("since") since: String
    ): Call<List<BaseTrending>>

}



