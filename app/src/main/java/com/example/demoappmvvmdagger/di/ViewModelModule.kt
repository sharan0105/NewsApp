package com.example.demoappmvvmdagger.di

import androidx.lifecycle.ViewModel
import com.example.demoappmvvmdagger.viewModels.MainViewModelImpl
import com.example.demoappmvvmdagger.viewModels.WelcomeScreenViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    //Whenever we require instance of mainViewModelImpl , dagger will make it for us.
    @Binds
    @ClassKey(MainViewModelImpl::class)
    @IntoMap
    abstract fun bindsMainViewModel (viewModel: MainViewModelImpl) : ViewModel

    @Binds
    @ClassKey(WelcomeScreenViewModelImpl::class)
    @IntoMap
    abstract fun bindsWelcomeScreenViewModel(viewModel: WelcomeScreenViewModelImpl):ViewModel

}