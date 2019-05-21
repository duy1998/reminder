package com.example.task_notification

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Column {
    private var mMaxColumnHeight: Float = 0.0f
    private var mMinColumnHeight: Float = 0.0f
    private var mColumnWidth: Float = 0.0f
    private var mColumnHeight:Float=0.0f
    private var mStep:Float=0.0f
    private var mRect = RectF()
    private var mRadius: Float = 0.0f
    public var mLeft:Float=0f
    public var mTop:Float=0f
    public var mRight:Float=0f
    public var mBottom:Float=0f
    private var mBegin: Int
    private var mDirection: Float = -1.0f

    constructor(
        mMaxColumnHeight: Float,
        mMinColumnHeight: Float,
        mColumnWidth: Float,
        mStep: Float,
        mRadius: Float,
        mLeft: Float,
        mTop: Float,
        mRight: Float,
        mBottom: Float,
        mBegin: Int
    ) {
        this.mMaxColumnHeight = mMaxColumnHeight
        this.mMinColumnHeight = mMinColumnHeight
        this.mColumnWidth = mColumnWidth
        this.mStep = mStep
        this.mRadius = mRadius
        this.mLeft = mLeft
        this.mTop = mTop
        this.mRight = mRight
        this.mBottom = mBottom
        this.mBegin = mBegin
        this.mRect.set(mLeft,0f,mRight,mBottom)
    }

    fun setTop() {
        mColumnHeight+=mStep*mDirection
        if(mColumnHeight> mMaxColumnHeight)
        {
            mColumnHeight=mMaxColumnHeight
            mDirection*=-1
        } else if(mColumnHeight<mMinColumnHeight){
            mColumnHeight=mMinColumnHeight
            mDirection*=-1
        }
        mTop=mMaxColumnHeight-mColumnHeight
    }

    fun draw(canvas: Canvas, paint: Paint, begin:Int) {
        if(begin>mBegin) {
            setTop()
            mRect.set(mLeft,mTop,mRight,mBottom)
        }
        canvas.drawRoundRect(mRect, mRadius, mRadius, paint)
    }
}