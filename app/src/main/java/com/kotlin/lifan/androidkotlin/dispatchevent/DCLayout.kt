package com.kotlin.lifan.androidkotlin.dispatchevent

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.MotionEvent

class DCLayout : ConstraintLayout {
    lateinit var dispatch : (MotionEvent) -> Unit
    lateinit var touch : (MotionEvent) -> Unit
    lateinit var intercept : (MotionEvent) -> Unit

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN || ev.action == MotionEvent.ACTION_UP) {
            kotlin.run{ dispatch(ev) }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!= null && (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_UP)) {
            kotlin.run { touch(event) }
        }
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!= null && (ev.action == MotionEvent.ACTION_DOWN || ev.action == MotionEvent.ACTION_UP)) {
            kotlin.run { intercept(ev) }
        }
        return super.onInterceptTouchEvent(ev)
    }

}




