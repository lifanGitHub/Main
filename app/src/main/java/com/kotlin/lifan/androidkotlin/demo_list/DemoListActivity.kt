package com.kotlin.lifan.androidkotlin.demo_list

import android.os.Bundle
import android.view.View

import com.kotlin.lifan.androidkotlin.BaseActivity
import com.kotlin.lifan.androidkotlin.ItemHome
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.item1.RecyclerActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author by LiFan
 * @date 2018/10/29
 */

class DemoListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content.addView(ItemHome(this,"空调模式", View.OnClickListener { RecyclerActivity.start(this) }))
    }
}
