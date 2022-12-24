package com.example.demoappmvvmdagger.viewModels

import androidx.lifecycle.LiveData
import com.example.demoappmvvmdagger.News

interface MainViewModel {
    fun getNews()
    val newsArticles:LiveData<News>
    val newsError:LiveData<Throwable>
}