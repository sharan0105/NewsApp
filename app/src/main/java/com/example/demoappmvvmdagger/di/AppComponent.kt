package com.example.demoappmvvmdagger.di

import androidx.lifecycle.ViewModel
import com.example.demoappmvvmdagger.MainActivity
import com.example.demoappmvvmdagger.UserInfo
import com.example.demoappmvvmdagger.WelcomeScreen
import dagger.Component
import javax.inject.Singleton

/*Modules indicate some kind of dependency on other modules*/
@Singleton
@Component(modules = [RetrofitModule::class,ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun injectUserInfoActivity(userInfoActivity:UserInfo)
    fun injectWelcomeFragment(fragment:WelcomeScreen)
    fun getAllViewModels() : Map<Class<*>, ViewModel>


}