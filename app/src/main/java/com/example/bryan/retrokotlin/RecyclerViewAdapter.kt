package com.example.bryan.retrokotlin

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(context).inflate(id, this, false)


class RecyclerViewAdapter(private val click: (User, Int) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private val userList: ArrayList<User> = ArrayList()

    //region Override Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RecyclerViewHolder(parent.inflate(viewType))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) =
            holder.bindData(userList[position], {user -> click(user, position)})

    override fun getItemViewType(position: Int) = R.layout.item_list

    override fun getItemCount() = userList.size
    //endregion

    //region Public Methods
    fun addData(data: ArrayList<User>) = userList.addAll(data)
    //endregion


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textAddress: TextView = itemView.findViewById(R.id.text_address)
        private val profilePic: ImageView = itemView.findViewById(R.id.image_picture)

        fun bindData(user: User, click: (User) -> Unit): Unit = with(user) {
            itemView.setOnClickListener { click(user) }
            textName.text = login
            textAddress.text = type
            Glide.with(itemView.context)
                    .load(avatar_url)
                    .into(profilePic)
        }
    }
}