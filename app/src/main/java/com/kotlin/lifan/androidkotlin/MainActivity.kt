package com.kotlin.lifan.androidkotlin

import android.os.Bundle
import android.view.View
import com.kotlin.lifan.androidkotlin.item1.RecyclerActivity
import com.kotlin.lifan.androidkotlin.service.ServiceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        content.addView(ItemHome(this,"RecyclerViewDemo", View.OnClickListener { RecyclerActivity.start(this) }))
        content.addView(ItemHome(this,"ServiceDemo", View.OnClickListener { ServiceActivity.start(this) }))

    }
}
