package com.example.demoappmvvmdagger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoappmvvmdagger.ViewModelFactory.MainViewModelFactory
import com.example.demoappmvvmdagger.databinding.ActivityMainBinding
import com.example.demoappmvvmdagger.viewModels.MainViewModelImpl
import javax.inject.Inject


class MainActivity : AppCompatActivity(){
    private lateinit var mainViewModel:MainViewModelImpl

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val component = (application as AppClass).appComp
        component.inject(this)
        //After this line of code only dagger, will be able to inject all the required values
        //so before making use of any of these field injected objects , make sure , they are
        //always initialized

         mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModelImpl::class.java)
         mainViewModel.getNews()
         mainViewModel.newsArticles.observe(this, Observer {
            binding.loader.visibility =View.GONE
            binding.newsList.visibility=View.VISIBLE
            it?.articles?.let { newsArticles ->
                Log.i("NewsArticleLiveData", "$newsArticles")
                binding.newsList.adapter = RecViewAdapter(newsArticles)
                binding.newsList.layoutManager=LinearLayoutManager(this)
            }
        })
    }
}