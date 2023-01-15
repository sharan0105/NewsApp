package layout

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlToLoad")
fun ImageView.urlToLoad(url:String){
    Glide.with(this.context).load(url).into(this)
}