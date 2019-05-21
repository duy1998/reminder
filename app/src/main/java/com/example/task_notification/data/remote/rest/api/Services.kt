package com.example.task_notification.data.remote.rest.api

import com.example.task_notification.data.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

//    @GET("api_reminder/get_list")
//    fun getList(@Query("auth_key") auth_key:String,@Query("count") count:Int) :retrofit2.Call<Data>

    @GET("api_reminder/get_list")
    fun getList(@Query("auth_key") auth_key:String,@Query("count") count:Int) :retrofit2.Call<Data>
}