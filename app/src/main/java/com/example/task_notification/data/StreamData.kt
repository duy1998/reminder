package com.example.task_notification.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StreamData {
    @SerializedName("view")
    @Expose
    var mView: Int = 0

    @SerializedName("streamType")
    @Expose
    var mStreamType: Int = 0

    @SerializedName("streamId")
    @Expose
    var mStreamId: Int = 0

    @SerializedName("displayName")
    @Expose
    var mDisplayName: String? = null

    @SerializedName("verified")
    @Expose
    var mIsVerified: Boolean = false

    @SerializedName("startTime")
    @Expose
    var mStartTime: Long = 0

    @SerializedName("location")
    @Expose
    var mLocation: String? = null

    @SerializedName("avatar")
    @Expose
    var mAvatar: String? = null

    @SerializedName("userName")
    @Expose
    var mUserName: String? = null

    @SerializedName("title")
    @Expose
    var mTitle: String? = null

    @SerializedName("userId")
    @Expose
    var mUserId: Int = 0

}