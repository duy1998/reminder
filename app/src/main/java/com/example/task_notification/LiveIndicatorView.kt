package com.example.task_notification

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
class LiveIndicatorView:View
{
      private  var mIconHeight:Int=0
      private  var mIconWidth:Int=0

      private var mCount:Int=0
      private  var mMaxColumn:Float=0.0f
      private var  mWidthColumn:Float=0.0f
      private val mRadius: Float = 2f
      private var  mSpacing:Float=0.0f
      private var mMinColumn:Float=0.0f

      private var time=0
      private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
      private  var step=0f
      private  var listColumn: MutableList<Column>? = null

    fun init(context: Context, attributeSet: AttributeSet)
    {
        val attributes = context.theme.obtainStyledAttributes(attributeSet, R.styleable.LiveIndicatorView, 0, 0)

        mMaxColumn= attributes.getDimensionPixelSize(R.styleable.LiveIndicatorView_maxColumnHeight,20).toFloat()
        mMinColumn= attributes.getDimensionPixelSize(R.styleable.LiveIndicatorView_minColumnHeight,5).toFloat()
        mSpacing= attributes.getDimensionPixelSize(R.styleable.LiveIndicatorView_columnSpacing,1).toFloat()
        mCount=attributes.getInt(R.styleable.LiveIndicatorView_countColumn,3)

        mWidthColumn=mMinColumn/2
        step=mMaxColumn/10f

        mIconWidth = (mWidthColumn * mCount + mSpacing * (mCount-1)).toInt()
        mIconHeight = mMaxColumn.toInt()

        listColumn=ArrayList<Column>()

        for (x in 0..mCount-1){
            if(x==0)
            {
                (listColumn as ArrayList<Column>).add(Column(mMaxColumn,mMinColumn,mWidthColumn,step,mRadius,
                    0f,
                    mMaxColumn,
                    0f+mWidthColumn,
                    mMaxColumn,
                    x))
            }
            else
            {
                (listColumn as ArrayList<Column>).add(Column(mMaxColumn,mMinColumn,mWidthColumn,step,mRadius,
                    (listColumn as ArrayList<Column>).get(x-1).mRight+mSpacing,
                    mMaxColumn,
                    (listColumn as ArrayList<Column>).get(x-1).mRight+mSpacing+mWidthColumn,
                    mMaxColumn,
                    x))
            }
        }
    }
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init(context,attributeSet)
    }





    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width: Int
        var height: Int
        Log.d("height,width", "Measure: " + getHeight() + " " + getWidth())

//        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
//
//        if (widthMode == View.MeasureSpec.EXACTLY) {
//            width = View.MeasureSpec.getSize(widthMeasureSpec)
//        } else {
//            width = mIconWidth
//        }
//
//        if (heightMode == View.MeasureSpec.EXACTLY) {
//            height = View.MeasureSpec.getSize(heightMeasureSpec)
//        } else {
//            height = mIconHeight
//        }
//
//        width = Math.max(mIconWidth, width)
//        height = Math.max(mIconHeight, height)

        val measuredWidth = View.MeasureSpec.makeMeasureSpec(mIconWidth, View.MeasureSpec.EXACTLY)
        val measureHeight = View.MeasureSpec.makeMeasureSpec(mIconHeight, View.MeasureSpec.EXACTLY)

        super.onMeasure(measuredWidth, measureHeight)
        Log.d("height,width", "Measure: " + getHeight() + " " + getWidth())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val contentW = w - paddingLeft - paddingRight
        val contentH = h - paddingTop - paddingBottom
        Log.d("height,width","onSizeChanged: "+ getHeight().toString() + " " + getWidth())
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("height,width", getHeight().toString() + " " + getWidth())
        paint.color= Color.WHITE
        paint.style=Paint.Style.FILL


        canvas?.apply {
            for (x in 0..mCount-1)
            {
                listColumn!!.get(x).draw(this,paint,time)
            }
            if(time !=mCount)
            {
               time++
            }

            postInvalidateDelayed(1000L/30)
        }
//            if (time > 0) {
//                mFirstColumnHeight += step * mFirstColumnDirection
//            }
//            if(mFirstColumnHeight>mMaxColumn)
//            {
//                mFirstColumnHeight=mMaxColumn
//                mFirstColumnDirection*=-1.0f
//            }
//            else if (mFirstColumnHeight<mMinColumn)
//            {
//                mFirstColumnHeight=mMinColumn
//                mFirstColumnDirection*=-1.0f
//            }
//
//            if (time > 6) {
//                mSecondColumnHeight += step * mSecondColumnDirection
//            }
//            if(mSecondColumnHeight>mMaxColumn)
//            {
//                mSecondColumnHeight=mMaxColumn
//                mSecondColumnDirection*=-1.0f
//            }
//            else if (mSecondColumnHeight<mMinColumn)
//            {
//                mSecondColumnHeight=mMinColumn
//                mSecondColumnDirection*=-1.0f
//            }
//            if (time > 3) {
//                mThirdColumnHeight+=step* mThirdColumnDirection
//            }
//            if(mThirdColumnHeight>mMaxColumn)
//            {
//                mThirdColumnHeight=mMaxColumn
//                mThirdColumnDirection*=-1.0f
//            }
//            else if (mThirdColumnHeight<mMinColumn)
//            {
//                mThirdColumnHeight=mMinColumn
//                mThirdColumnDirection*=-1.0f
//            }
//            var left:Float
//            var top:Float
//            var right:Float
//            var bottom:Float
//            left=0f
//            top=mMaxColumn-mFirstColumnHeight
//            right=left+mWidthColumn
//            bottom=mMaxColumn
//            mRect1.set(left,top,right,bottom)
//            drawRoundRect(mRect1,mRadius,mRadius,paint)
//
//            left= left+mSpacing+mWidthColumn
//            top=mMaxColumn-mSecondColumnHeight
//            right=left+mWidthColumn
//            mRect2.set(left,top,right,bottom)
//            drawRoundRect(mRect2,mRadius,mRadius,paint)
//
//            left=left+mSpacing+mWidthColumn
//            top=mMaxColumn-mThirdColumnHeight
//            right=left+mWidthColumn
//            mRect3.set(left,top,right,bottom)
//            drawRoundRect(mRect3,mRadius,mRadius,paint)
//
//            if (time<7) time++;
//            Log.d("duy","Time: "+time)
//            postInvalidateDelayed(1000L/30)
//
//        }


    }

}