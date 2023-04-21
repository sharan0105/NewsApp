package com.example.demoappmvvmdagger

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryData(
    @SerializedName("name")
    val name: String?,
    //This contains the image url of the flag.
    @SerializedName("flag")
    val flag:String?
) : Serializable
