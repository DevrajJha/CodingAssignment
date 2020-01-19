package devraj.codingassignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import devraj.codingassignment.R
import devraj.codingassignment.databinding.UserListRowBinding
import devraj.codingassignment.imagecaching.DoubleCache
import devraj.codingassignment.imagecaching.ImageLoader
import devraj.codingassignment.model.Users
import devraj.codingassignment.viewmodel.UserDetailsViewModel

class UsersAdapter( val list: List<Users>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    lateinit var  binding: UserListRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
         binding=
            DataBindingUtil.inflate(layoutInflater, R.layout.user_list_row, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class UserViewHolder(binding: UserListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            if (binding.viewModel==null)
                binding.viewModel=UserDetailsViewModel(user)
            else
                binding.viewModel!!.user=user
        }
    }


}