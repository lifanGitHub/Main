package com.kotlin.lifan.androidkotlin.dispatchevent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.base.BaseActivity
import kotlinx.android.synthetic.main.ac_dispatch.*

class EventActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_dispatch)
        viewGroup.dispatch = {
            textView.append("viewGroup dispatchTouchEvent action=${it.action} \n")
        }
        viewGroup.touch = {
            textView.append("viewGroup TouchEvent action=${it.action} \n")
        }
        viewGroup.setOnClickListener {
            textView.append("viewGroup Click \n")
        }
        viewGroup.intercept = {
            textView.append("viewGroup intercept action=${it.action} \n")
        }

        button.dispatch = {
            textView.append("button dispatchTouchEvent action=${it.action} \n")
        }
        button.touch = {
            textView.append("viewGroup TouchEvent action=${it.action} \n")
        }
        button.setOnClickListener{
            textView.append("button Click \n")
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            textView.text = ""
            textView.append("Activity dispatchTouch action=${ev.action} \n")
        }
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, EventActivity::class.java))
        }
    }
}
