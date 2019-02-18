package com.kotlin.lifan.androidkotlin.threadview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.base.BaseActivity
import com.kotlin.lifan.androidkotlin.base.ItemHome
import com.kotlin.lifan.androidkotlin.base.showToast
import kotlinx.android.synthetic.main.ac_thread_view.*

class ThreadViewActivity : BaseActivity(){
    private var threadHandler: Handler? = null
    private var button: Button? = null
    private var thread = Thread(Runnable {
        Looper.prepare()
        threadHandler = Handler()
        Looper.loop()
    })
//    private var threadHandler2: Handler? = null
//    private var thread2 = Thread(Runnable {
//        Looper.prepare()
//        threadHandler2 = Handler()
//        Looper.loop()
//    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_thread_view)
        thread.start()
        content.addView(ItemHome(this, "从子线程追加View", View.OnClickListener {
            threadHandler?.post{
                //子线程创建View
                button = Button(this)
                button?.text = "此View从${Thread.currentThread().name}创建"
                //主线程addView
                runOnUiThread {
                    content.addView(button)
                }
            }
        }))
        content.addView(ItemHome(this, "子线程修改View", View.OnClickListener {
            threadHandler?.post{
                button?.text = "${Thread.currentThread().name}修改成功"
            }
        }))
        content.addView(ItemHome(this, "主子线修改View(异常)", View.OnClickListener {
            button?.text = "${Thread.currentThread().name}修改成功"
        }))


    }










    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, ThreadViewActivity::class.java))
        }
    }

}