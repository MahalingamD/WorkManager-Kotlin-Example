package com.example.simplework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.simplework.R

class NotificationListAdapter(
    val mContext: Context,
    mStringArrayList: ArrayList<String>
) : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    var mList = arrayListOf<String>()

    init {
        mList = mStringArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val aHeaderLayout = itemView.findViewById<RelativeLayout>(R.id.header_layout)
        val aChildLayout = itemView.findViewById<LinearLayout>(R.id.childLayout)


        fun bindItems(position: Int) {
            if (position == 0) {
                aHeaderLayout.visibility = View.VISIBLE
                aChildLayout.visibility = View.GONE
            } else {
                aHeaderLayout.visibility = View.GONE
                aChildLayout.visibility = View.VISIBLE
            }

            if (position % 2 == 0) {
                aChildLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue))
            } else {
                aChildLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
            }
        }
    }

    fun removeAt(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)

    }

}