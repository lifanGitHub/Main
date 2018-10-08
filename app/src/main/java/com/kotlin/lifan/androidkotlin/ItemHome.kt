package com.kotlin.lifan.androidkotlin

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_home_item.*
import kotlinx.android.synthetic.main.view_home_item.view.*

/**
 * @author by LiFan
 * @date 2018/10/8
 */
class ItemHome : FrameLayout {

    constructor(context: Context?, text: String, onClickListener: OnClickListener) : super(context){
        LayoutInflater.from(context).inflate(R.layout.view_home_item,this,true)
        button.text = text
        button.setOnClickListener(onClickListener)
    }



}