package com.example.demoappmvvmdagger.ViewModelFactory

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoappmvvmdagger.NewsService
import com.example.demoappmvvmdagger.viewModels.MainViewModel
import com.example.demoappmvvmdagger.viewModels.MainViewModelImpl
import javax.inject.Inject

/*If you observe , only 1 type of view model can be created by 1 ViewModel factory .
So, if we have multiple view models , then we need to create separate factories
for each view model. To avoid this kind of scenario, we can provide the viewModel
itself to the factory and the factory can directly create the view model.

This is a generalized way to make all view models using the same factory . Dagger knows how
to create the map . We just need to inject that map into the view model factory and
after that automatically we will have all view models along with their keys
 */


class MainViewModelFactory @Inject constructor(private val map : Map<Class<*>,@JvmSuppressWildcards ViewModel>):ViewModelProvider.Factory {
    //With this , we will be able to get our desired view model based on the request we get
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}