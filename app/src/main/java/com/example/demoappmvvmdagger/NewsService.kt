package com.example.demoappmvvmdagger

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

const val BASE_URL ="https://newsapi.org"
const val API_KEY  = "749423eb8d8b41fba37f68dc9cf83144"

@Singleton
interface NewsService {
    @GET("/v2/top-headlines")
    fun getCountryNews(@Query("country") country:String,@Query("apiKey") apiKey:String) : Single<News>

}

