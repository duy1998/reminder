package com.example.task_notification.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("error")
    @Expose
    var error :String?=null
    @SerializedName("message")
    @Expose
    var message:String?=null
    @SerializedName("data")
    @Expose
    var listStreamData:List<StreamData>?=null

    constructor(error: String?, message: String?, listStreamData: List<StreamData>?) {
        this.error = error
        this.message = message
        this.listStreamData = listStreamData
    }
}
