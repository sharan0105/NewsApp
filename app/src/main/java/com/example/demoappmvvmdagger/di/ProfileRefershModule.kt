package com.example.demoappmvvmdagger.di

import com.example.demoappmvvmdagger.ProfileRefreshRepo
import com.example.demoappmvvmdagger.ProfileRefreshRepoImpl
import dagger.Binds
import dagger.Module


@Module
abstract class ProfileRefreshModule {

    @Binds
    abstract fun bindsProfileRefreshRepo(repo:ProfileRefreshRepoImpl) : ProfileRefreshRepo
}
