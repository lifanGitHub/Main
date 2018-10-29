package com.kotlin.lifan.androidkotlin.util

import android.graphics.RectF
import android.view.MotionEvent
import android.view.View

/**
 * @author by LiFan
 * @date 2018/5/17 0017
 */

class ViewUtil {
    companion object {
        //判断Touch事件是否在View内
        fun isInTouchRect(view: View, event: MotionEvent): Boolean {
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            val rectF = RectF(location[0].toFloat(), location[1].toFloat(), (location[0] + view.width).toFloat(), (location[1] + view.height).toFloat())
            return rectF.contains(event.rawX, event.rawY)
        }
    }



}
