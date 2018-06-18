package com.example.bryan.retrokotlin

import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class RecyclerViewAdapter_SecondActivity() : RecyclerView.Adapter<RecyclerViewAdapter_SecondActivity.RecyclerViewHolder_2>() {

    var repoList: ArrayList<Repo> = ArrayList()

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder_2, position: Int) =
        holder.bindData(repoList[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder_2 =
        RecyclerViewHolder_2(parent.inflate(viewType))


    override fun getItemViewType(position: Int) = R.layout.item_list_2

    fun addData(repos: ArrayList<Repo>) = repoList.addAll(repos)

    class RecyclerViewHolder_2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repo_entry: TextView = itemView.findViewById(R.id.repo_entry)
        fun bindData(repo : Repo) {
            repo_entry.text = repo.repo_name
        }
    }
}