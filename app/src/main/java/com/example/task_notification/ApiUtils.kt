package com.example.task_notification

import com.example.task_notification.data.remote.rest.api.Services

class ApiUtils {
    companion object {
        private val baseUrl = "http://api.360live.vn/"
        fun getSerVices(): Services {
            return RetrofitClient.getRetrofit(baseUrl)!!.create(Services::class.java)
        }
    }

}