package layout

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

@BindingAdapter("urlToLoad")
fun ImageView.urlToLoad(url:String){
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter("flagIconUrl")
fun ImageView.flagIconUrl(url:String){
    GlideToVectorYou.init().with(this.context).load(Uri.parse(url), this)
}