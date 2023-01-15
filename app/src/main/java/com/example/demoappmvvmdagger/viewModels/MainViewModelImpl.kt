package com.example.demoappmvvmdagger.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoappmvvmdagger.API_KEY
import com.example.demoappmvvmdagger.News
import com.example.demoappmvvmdagger.NewsService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
We will make this view model from the view model factory as we need
some constructor params in our view model

If we see carefully , we can even create the view model itself as we know how to create all the
dependencies so , we can directly pass the view-model to the factory
and check which view model was passed and simply consume it wherever we want to .*/

class MainViewModelImpl @Inject constructor (private val service: NewsService):MainViewModel,ViewModel() {
    //Aim to check whether live data automatically updates the value on screen rotation or not
    //So now, we have the service , we just need to implement the service call
    private val compositeDisposable = CompositeDisposable()
    override val newsArticles = MutableLiveData<News>()
    override val newsError = MutableLiveData<Throwable>()
    //This will always remain the same so why is the API call being made again despite
    //the value being same and us using the distinctUntilChanged operator
    private var countryCode = Observable.just("in")

    init {}

    override fun getNews() {
        //Add function to get news data here . Right now service call is being made when the view
        //model is getting initialized

        compositeDisposable.add(countryCode.distinctUntilChanged().flatMap {res ->
            Log.i("CountryCode", res)
            getNewsObservable(res)
            }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ news-> newsArticles.postValue(news)
            Log.i("API", "Called again")},
            { err-> Log.e("News Error", "News Service Encounter Error $err" ) }
        ))
    }


    private fun getNewsObservable(countryCode:String):Observable<News> {
        return service.getCountryNews(countryCode, API_KEY)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}