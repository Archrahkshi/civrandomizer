package com.example.myapplication.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.PostViewHolder>(){

    private val posts = mutableListOf<Post>()

    fun addPosts(newPosts: List<Post>) {
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = itemView.apply {
            textViewTitle.text = post.title
            textViewBody.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}