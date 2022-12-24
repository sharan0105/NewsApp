package com.example.demoappmvvmdagger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview.view.*

class RecViewAdapter(private val newsArticles:List<Articles>,private val clickListener:RVClickListener):RecyclerView.Adapter<RVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        //Layout inflater converts the XML file to Java
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.itemview,parent,false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
      //Passing an impl of RVClickListener so it should automatically be able to implement the click function
      holder.title.text = newsArticles[position].title
      holder.author.text = newsArticles[position].author
      holder.title.setOnClickListener { _ ->clickListener.onViewClick() }
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

}

//This is the view holder that will contain each view inside the Recycler View
//itemView here specifies the view how each item of your list will look like
class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title = itemView.tvTitle
    val author = itemView.tvAuthor



}
