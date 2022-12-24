package com.example.demoappmvvmdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.demoappmvvmdagger.ViewModelFactory.MainViewModelFactory
import com.example.demoappmvvmdagger.viewModels.WelcomeScreenViewModelImpl
import javax.inject.Inject

//This is going to be the landing page of my app. Will contain 2 fragments , 1 for Welcoming the
//user and the other for signing up.
class UserInfo : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    lateinit var viewModel:WelcomeScreenViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (application as? AppClass)?.appComp
        component?.injectUserInfoActivity(this)
        setContentView(R.layout.activity_user_info)
        //Want to have a common view model for activity and all its fragments
        viewModel = ViewModelProvider(this,viewModelFactory)[WelcomeScreenViewModelImpl::class.java]
        //Doubt: Should we have addToBackStack for our first fragment as when the activity
        //gets created as even if you press back on that fragment more or less you should
        //get redirected to the prev activity so I don't find it of any use for the first fragment
        supportFragmentManager.beginTransaction().apply {
            val fragment = WelcomeScreen()
            replace(R.id.fragmentLayout,fragment,WelcomeScreen::javaClass.name)
        }.commit()
    }
}