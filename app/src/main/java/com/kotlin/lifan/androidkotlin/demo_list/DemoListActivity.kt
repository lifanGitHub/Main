package com.kotlin.lifan.androidkotlin.demo_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.kotlin.lifan.androidkotlin.base.BaseActivity
import com.kotlin.lifan.androidkotlin.base.ItemHome
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.killapptest.KillActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author by LiFan
 * @date 2018/10/29
 */

class DemoListActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DemoListActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content.addView(ItemHome(this, "空调模式", View.OnClickListener { ModeChoiceAc.start(this) }))
        content.addView(ItemHome(this, "App持久化存储", View.OnClickListener { KillActivity.start(this) }))
    }
}
