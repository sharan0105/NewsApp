package com.example.demoappmvvmdagger

import io.reactivex.subjects.BehaviorSubject

interface ProfileRefreshRepo {
    val dataSource:BehaviorSubject<String>
    fun refresh(country:String)
}