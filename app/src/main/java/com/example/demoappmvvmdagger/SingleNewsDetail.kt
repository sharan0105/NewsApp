package com.example.demoappmvvmdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoappmvvmdagger.databinding.ActivitySingleNewsDetailBinding
import org.apache.commons.lang3.StringUtils

/* We don't actually need a view model for this activity as we will already have all the data
* being passed from the prev screen so we can consume all that data here . */

class SingleNewsDetail : AppCompatActivity() {
    lateinit var binding : ActivitySingleNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Implementing data binding for this activity as this is a more cleaner approach
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_news_detail)
        val imgUrl = intent?.getStringExtra("Image")?: StringUtils.EMPTY
        val description = intent?.getStringExtra("Description")?: StringUtils.EMPTY
        val author = intent?.getStringExtra("Author")?: StringUtils.EMPTY
        val title = intent?.getStringExtra("NewsTitle")?: StringUtils.EMPTY
        val article = Articles(author = author, urlToImage = imgUrl, content = description, title = title)
        binding.newsArticle = article


    }
}