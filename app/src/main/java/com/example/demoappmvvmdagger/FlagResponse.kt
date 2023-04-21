package com.example.demoappmvvmdagger

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlagResponse(
   @SerializedName("msg")
   val message:String?,
   @SerializedName("data")
   val countryList: List<CountryData>?
) : Serializable
