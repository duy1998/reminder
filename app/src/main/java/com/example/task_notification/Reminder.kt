package com.example.task_notification

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.task_notification.data.Data
import com.example.task_notification.data.StreamData
import kotlinx.android.synthetic.main.layout_reminder.*
import kotlinx.android.synthetic.main.layout_reminder.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.widget.SwipeRefreshLayout


class Reminder{
    val services = ApiUtils.getSerVices()
    var context: Context? = null
    var view :NavigationView?=null
//    var mNavigationView : NavigationView? =null

    constructor(viewRoot: NavigationView){
        context = viewRoot.context
        view = viewRoot
        view!!.swipe_refresh_layout.setOnRefreshListener{getData(viewRoot)}
        view!!.reminders.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        if (isNetworkConnected())
        {
            getData(viewRoot)
        }
        else
        {
            viewRoot.layout_disconnect.visibility= View.VISIBLE
        }
        viewRoot.layout_disconnect.findViewById<Button>(R.id.btn_retry).setOnClickListener { getData(viewRoot) }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
    fun getData(viewRoot: View){
        services.getList("11ed2864992e870c67e68e08f8039c6c5e8b20910cf1f939",10)
            .enqueue(object : Callback<Data> {
                override fun onFailure(call: Call<Data>?, t: Throwable?) {
                    viewRoot.layout_disconnect.visibility= View.VISIBLE
                    Log.d("NO_INTERNET", t.toString())
                }

                @SuppressLint("WrongConstant")
                override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                    Log.d("TAG", "Chuan")
                    viewRoot.layout_disconnect.visibility=View.INVISIBLE
                    var data= response!!.body()
                    if (data != null) {

                        if(data.listStreamData!!.size!=0){

                            viewRoot.reminders.adapter =Adapter(data.listStreamData as ArrayList<StreamData>,viewRoot.context)
                            viewRoot.empty_layout.visibility=View.INVISIBLE
                        }
                        else
                        {
                            viewRoot.empty_layout.visibility=View.VISIBLE;
                        }

                    }

                }

            })
        hideRefreshing()

    }

    fun hideRefreshing()
    {
        view!!.swipe_refresh_layout.isRefreshing=false
    }

}