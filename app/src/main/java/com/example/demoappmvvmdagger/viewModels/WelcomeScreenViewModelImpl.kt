package com.example.demoappmvvmdagger.viewModels


import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WelcomeScreenViewModelImpl @Inject constructor():ViewModel(),WelcomeScreenViewModel {

    override fun onSignUpClick() {
        Log.d("VMWorking","Yes")
    }
}