package com.example.simplework.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplework.R
import com.example.simplework.adapter.NotificationListAdapter
import com.example.simplework.swipe.SwipeToDeleteCallback

class SecondActivity : AppCompatActivity() {

    val mContext: Context by lazy { applicationContext!! }

    val mItemRecyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.item_recycler) }

    val mStringArrayList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        init()
    }

    private fun init() {

        lodaData()

        val myLayoutManager = LinearLayoutManager(mContext)
        mItemRecyclerView.layoutManager = myLayoutManager
        mItemRecyclerView.setHasFixedSize(true)

        val adapter = NotificationListAdapter(mContext, mStringArrayList)

        mItemRecyclerView.adapter = adapter


        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = mItemRecyclerView.adapter as NotificationListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
            }
        }


        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(mItemRecyclerView)

    }

    private fun lodaData() {

        for (i in 0 until 10) {
            mStringArrayList.add(("String $i"))
        }

    }
}
