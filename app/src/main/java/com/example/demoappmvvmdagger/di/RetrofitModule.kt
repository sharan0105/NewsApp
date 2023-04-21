package com.example.demoappmvvmdagger.di

import com.example.demoappmvvmdagger.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    //This will provide us the instance of news service
    @Provides
    fun providesNewsService():NewsService{
        return  Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(NewsService::class.java)
    }

    //This will provide us the instance of FlagService.
    @Provides
    fun providesFlagService(): FlagService{
        return Retrofit.Builder().baseUrl(FLAG_API_BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().
        create(FlagService::class.java)
    }



    //Now , we need the news service implementation , so that we can use it anywhere in the project
    //Dagger should also know how to create the object inside the provide function then only it can properly inject those objects


}