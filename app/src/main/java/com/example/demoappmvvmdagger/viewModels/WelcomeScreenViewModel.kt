package com.example.demoappmvvmdagger.viewModels

import androidx.lifecycle.LiveData

interface WelcomeScreenViewModel {
    fun onSignUpClick()
    fun setFlagIcon(country:String="India")
    val flagIconUrl : LiveData<String>
}