package devraj.codingassignment.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import devraj.codingassignment.model.Users
import androidx.core.app.ActivityOptionsCompat
import devraj.codingassignment.ui.DetailsActivity
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import devraj.codingassignment.AppConstants.Companion.KEY_USER
import devraj.codingassignment.R
import devraj.codingassignment.imagecaching.ImageLoader
import kotlinx.android.synthetic.main.user_list_row.view.*


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
//    Glide.with(imageView.context).load(url).into(imageView)
    ImageLoader.displayImage(url!!, imageView)
}
class UserDetailsViewModel(var user:Users) {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun onCardClick(view: View){
        val intent = Intent(view.context, DetailsActivity::class.java)
        intent.putExtra(KEY_USER, user)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.context as AppCompatActivity,
            view.findViewById(R.id.imgProfilePic),
            ViewCompat.getTransitionName(view.findViewById(R.id.imgProfilePic))!!
        )
        view.context.startActivity(intent, options.toBundle()!!)
    }
}