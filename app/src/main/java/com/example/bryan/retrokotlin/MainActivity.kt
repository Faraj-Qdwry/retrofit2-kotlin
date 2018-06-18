package com.example.bryan.retrokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//API Link: https://api.github.com/users

class MainActivity : AppCompatActivity() {

    //region Member fields
    private val call: Call<ArrayList<User>> by lazy { API_Interface.create().getUserDetails() }

    private val firstAdapter: RecyclerViewAdapter by lazy { RecyclerViewAdapter(click) }
    //endregion

    //region LifeCycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerview_demo).setUp()
        call.enqueue(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        call.cancel()
    }
    //endregion

    //region Private Methods
    private val click: (User, Int) -> Unit = { user , _ -> with(user){
        SecondActivity.startActivity(this@MainActivity, login, avatar_url)}
         }

    private val callback: Callback<ArrayList<User>> = object : Callback<ArrayList<User>> {
        override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {
            Log.d("Failure", "Failure")
        }
        override fun onResponse(call: Call<ArrayList<User>>?, response: Response<ArrayList<User>>?) {
            with(firstAdapter){
                addData(response?.body()!!)
                notifyDataSetChanged()
            }
        }
    }


    private fun RecyclerView.setUp() {
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        this.adapter = firstAdapter
    }
    //endregion

}
