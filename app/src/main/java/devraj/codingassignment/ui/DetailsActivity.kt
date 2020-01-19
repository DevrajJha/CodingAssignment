package devraj.codingassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import devraj.codingassignment.R
import devraj.codingassignment.databinding.ActivityDetailsBinding
import devraj.codingassignment.AppConstants.Companion.KEY_USER
import devraj.codingassignment.model.Users
import devraj.codingassignment.viewmodel.UserDetailsViewModel

class DetailsActivity : AppCompatActivity() {
lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(resources.getString(R.string.user_details))
        val user=intent.getParcelableExtra<Users>(KEY_USER)
        val viewModel=UserDetailsViewModel(user)
        binding.viewModel=viewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home)
            ActivityCompat.finishAfterTransition(this);
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this);
    }
}
