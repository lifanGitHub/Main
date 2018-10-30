package com.kotlin.lifan.androidkotlin.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.chess.ChessActivity
import com.kotlin.lifan.androidkotlin.demo_list.DemoListActivity
import com.kotlin.lifan.androidkotlin.item1.RecyclerActivity
import com.kotlin.lifan.androidkotlin.my_view.SeekBarAc
import com.kotlin.lifan.androidkotlin.service.ServiceActivity
import com.kotlin.lifan.androidkotlin.surface_view.SurfaceMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content.addView(ItemHome(this, "DemoList", View.OnClickListener { DemoListActivity.start(this) }))
        content.addView(ItemHome(this, "RecyclerView", View.OnClickListener { RecyclerActivity.start(this) }))
        content.addView(ItemHome(this, "Service", View.OnClickListener { ServiceActivity.start(this) }))
        content.addView(ItemHome(this, "Surface", View.OnClickListener { SurfaceMainActivity.start(this) }))
        content.addView(ItemHome(this, "五子棋", View.OnClickListener { ChessActivity.start(this) }))
        content.addView(ItemHome(this, "SeekBar", View.OnClickListener { SeekBarAc.start(this) }))

    }
}
