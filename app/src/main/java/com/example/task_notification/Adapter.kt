package com.example.task_notification

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.task_notification.data.StreamData
import kotlinx.android.synthetic.main.layout_item_1.view.*

class Adapter(val items : ArrayList<StreamData>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_1, p0, false))
    }

    override fun onBindViewHolder(holder:ViewHolder, pos: Int) {

        Glide.with(context)
            .load(items.get(pos).mAvatar)
            .into(holder.mAvatar)

        holder.mDisplayNameView.setText(items.get(pos).mDisplayName)
        holder.mLiveTime.setText(items.get(pos).mStartTime.toString())
        holder.mLiveTime.setText(DateUtil.getTimeAgo(items.get(pos).mStartTime, holder.mTitleView.getContext()))
        holder.mTitleView.setText(items.get(pos).mTitle)
        if(items.get(pos).mLocation=="") {
            holder.mLocation.setText("Trong không gian")
        } else{
            holder.mLocation.setText(items.get(pos).mLocation)
        }
    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

        var mTitleView= view.tv_live_title

        var mLocation=view.tv_location

        var mAvatar= view.img_avatar

        var mDisplayNameView=view.tv_display_name

        var mLiveTime=view.tv_live_time
    }
