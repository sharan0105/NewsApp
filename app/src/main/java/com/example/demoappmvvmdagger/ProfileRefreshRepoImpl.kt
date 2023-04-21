package com.example.demoappmvvmdagger

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRefreshRepoImpl @Inject constructor(): ProfileRefreshRepo{

    override val dataSource:BehaviorSubject<String> = BehaviorSubject.create()

    override fun refresh(country:String) {
        dataSource.onNext(country)
    }

  //TODO : Need to add function to clear the dataSource as well as try to create a private method
  //TODO: restrict direct access to the behavior subject
}