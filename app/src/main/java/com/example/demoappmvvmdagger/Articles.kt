package com.example.demoappmvvmdagger

import android.text.Spanned
import com.google.gson.annotations.SerializedName

data class
Articles(
    @SerializedName("author")
    val author:String?= null,
    @SerializedName("title")
    val title:String?= null,
    @SerializedName("content")
    val content:String?= null,
    @SerializedName("urlToImage")
    val urlToImage:String? = null,
    @SerializedName("source")
    val source:Source?= null,
    //Adding this to pass the disclaimer content , we won't be getting this field from the response
    val disclaimerContent:Spanned?= null
    )