package com.example.task_notification

import android.content.Context
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Color.parseColor
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.constraint.solver.widgets.ConstraintTableLayout
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class Box : LinearLayout{
    var tv: TextView? =null
    var live: LiveIndicatorView?=null
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    {
        init(context,attrs)
    }
    private fun init(context: Context, attrs: AttributeSet) {
        LayoutInflater.from(context).inflate(R.layout.box,this)
        tv=this.findViewById(R.id.tv)
        live=this.findViewById(R.id.live)
        this.setBackgroundColor(Color.RED)
        this.setBackgroundResource(R.drawable.shape)
    }


}