package devraj.codingassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.android.AndroidInjection
import devraj.codingassignment.R
import devraj.codingassignment.databinding.ActivityMainBinding
import devraj.codingassignment.di.ViewModelFactory
import devraj.codingassignment.imagecaching.DoubleCache
import devraj.codingassignment.imagecaching.ImageLoader
import devraj.codingassignment.model.Users
import devraj.codingassignment.ui.adapter.UsersAdapter
import devraj.codingassignment.viewmodel.UserViewModel
import java.util.ArrayList
import javax.inject.Inject





class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var userViewModel: UserViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var userList: ArrayList<Users>
    lateinit var refreshLayout: SwipeRefreshLayout
    lateinit var adapter:UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )
        AndroidInjection.inject(this)
        ImageLoader.setCache(DoubleCache(this))
        setTitle(resources.getString(R.string.users))
        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        initUi(binding)
        loadData()
        refreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                userViewModel.userResult=null
                loadData()
            }

        })
        userViewModel.userError.observe(this, Observer<String> {
            refreshLayout.isRefreshing = false
        })
        userViewModel.userLoader.observe(this, Observer<Boolean> {
            refreshLayout.isRefreshing = it
        })


    }
    private fun initUi(binding:ActivityMainBinding){
        recyclerView = binding.recyclerView
        refreshLayout = binding.swiperefresh
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        userList = ArrayList<Users>()
        adapter = UsersAdapter(userList)
        recyclerView.adapter = adapter
    }

    private fun loadData(){
        userList.clear()

        userViewModel.userResult("java", "weekly")
            .observe(this@MainActivity, Observer<List<Users>> {
                Log.e("tag", it.get(0).name)
                userList.addAll(it)
                adapter.notifyDataSetChanged()
                refreshLayout.isRefreshing = false
            })
    }
}
