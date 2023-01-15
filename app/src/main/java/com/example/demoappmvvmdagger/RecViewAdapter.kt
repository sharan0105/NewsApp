package com.example.demoappmvvmdagger

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview.view.*
import org.apache.commons.lang3.StringUtils

class RecViewAdapter(private val newsArticles:List<Articles>):RecyclerView.Adapter<RVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        //Layout inflater converts the XML file to Java
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.itemview,parent,false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        /*Passing an impl of RVClickListener so it should automatically be able to implement the
        click function which will take us to the SingleNewsDetail activity*/

        /*Additional logic for sourceName in case sourceName comes out to be empty
        Source name mostly comes in description , so when displaying the complete screen
        for the news item , we should display the author name as well */

        val authorName = newsArticles[position].author ?: StringUtils.EMPTY
        val sourceName = newsArticles[position].source?.name ?: newsArticles[position].author ?:
        StringUtils.EMPTY
        val newsTitle = newsArticles[position].title
        val description = newsArticles[position].content
        val imageUrl = newsArticles[position].urlToImage
        val content = newsArticles[position].content

        val rvImpl = object : RVClickListener {
            override fun onViewClick() {
                val intent = Intent(holder.itemView.context,SingleNewsDetail::class.java).apply {
                    putExtra("Author", authorName)
                    //These params can be null , handle them properly where you are consuming them
                    putExtra("NewsTitle", newsTitle)
                    putExtra("Description", description)
                    putExtra("Image", imageUrl)
                    putExtra("Content", content)
                }
                val bundle = Bundle()
                startActivity(holder.itemView.context, intent, bundle)
            }
        }
      holder.title.text = newsTitle
      holder.author.text = sourceName
      holder.itemView.setOnClickListener { rvImpl.onViewClick() }
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

}

//This is the view holder that will contain each view inside the Recycler View
//itemView here specifies the view how each item of your list will look like
class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.tvTitle
    val author = itemView.tvAuthor

}
