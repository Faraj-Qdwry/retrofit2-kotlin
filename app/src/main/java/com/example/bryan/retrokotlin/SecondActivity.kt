package com.example.bryan.retrokotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context, name: String, imageUrl: String) =
                context.startActivity(Intent(context, SecondActivity::class.java).apply {
                    putExtra("USERLOGIN", name)
                    putExtra("PICLINK", imageUrl)
                })
    }

    //Member Fields


    private val adapter = RecyclerViewAdapter_SecondActivity()
    private val apI_Interface by lazy { API_Interface.create() }
    private lateinit var call: Call<ArrayList<Repo>>
    //endregion

    //lifecycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        with(intent.getStringExtra("USERLOGIN")) {
            text_name.text = this
            call = apI_Interface.getUserRepos(this)
            call.enqueue(callback)
        }

        Glide.with(this@SecondActivity).load(intent.getStringExtra("PICLINK")).into(profile_pic)

        findViewById<RecyclerView>(R.id.recyclerview_2).setUp()

    }

    override fun onDestroy() {
        super.onDestroy()
        call.cancel()
    }
    //endregion

    //Private Methods
    private val callback: Callback<ArrayList<Repo>> = object : Callback<ArrayList<Repo>> {
        override fun onFailure(call: Call<ArrayList<Repo>>?, t: Throwable?) {
            Log.d("Failure", "secondsActivity Callbake Failure")
        }

        override fun onResponse(call: Call<ArrayList<Repo>>?, response: Response<ArrayList<Repo>>?) {
            with(adapter) {
                addData(response?.body()!!)
                notifyDataSetChanged()
            }
        }
    }

    private fun RecyclerView.setUp() {
        layoutManager = LinearLayoutManager(this@SecondActivity, LinearLayoutManager.VERTICAL, false)
        this.adapter = this@SecondActivity.adapter
    }
    //endregion
}





