package com.example.demoappmvvmdagger

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.demoappmvvmdagger.di.DaggerAppComponent

class AppClass : Application() {
    //This is the app component that we will use in all our activities and fragments to inject dependencies
    val appComp = DaggerAppComponent.builder().build()


}