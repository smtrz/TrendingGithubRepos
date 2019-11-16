package com.tahir.go_jek.Interfaces


import com.tahir.go_jek.Models.BaseTrending
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



