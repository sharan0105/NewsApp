package com.example.demoappmvvmdagger.di

import com.example.demoappmvvmdagger.BASE_URL
import com.example.demoappmvvmdagger.NewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    //This will provide us the retrofit instance .
    @Provides
    fun providesRetrofitInstance():Retrofit{
        return  Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    //Now , we need the news service implementation , so that we can use it anywhere in the project
    //Dagger should also know how to create the object inside the provide function then only it can properly inject those objects
    @Provides
    fun providesNewsService(retrofit: Retrofit) : NewsService{
        //dagger knows how to create retrofit instance , so now we will just
        //use that retrofit object  and instantiate our service class
        return retrofit.create(NewsService::class.java)
    }
}