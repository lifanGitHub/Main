package com.kotlin.lifan.androidkotlin

import android.os.Bundle
import android.view.View
import com.kotlin.lifan.androidkotlin.chess.ChessActivity
import com.kotlin.lifan.androidkotlin.item1.RecyclerActivity
import com.kotlin.lifan.androidkotlin.service.ServiceActivity
import com.kotlin.lifan.androidkotlin.surface_view.SurfaceMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content.addView(ItemHome(this,"RecyclerView", View.OnClickListener { RecyclerActivity.start(this) }))
        content.addView(ItemHome(this,"Service", View.OnClickListener { ServiceActivity.start(this) }))
        content.addView(ItemHome(this,"Surface", View.OnClickListener { SurfaceMainActivity.start(this) }))
        content.addView(ItemHome(this,"五子棋", View.OnClickListener { ChessActivity.start(this) }))

    }
}
