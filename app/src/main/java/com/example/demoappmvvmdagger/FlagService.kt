package com.example.demoappmvvmdagger

import io.reactivex.Observable
import retrofit2.http.GET

val FLAG_API_BASE_URL = "https://countriesnow.space"

interface FlagService {

    @GET("/api/v0.1/countries/flag/images")
    fun getFlagResponse(): Observable<FlagResponse>

}