package com.kotlin.lifan.androidkotlin.chess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kotlin.lifan.androidkotlin.BaseActivity
import com.kotlin.lifan.androidkotlin.R

/**
 * @author by LiFan
 * @date 2018/10/22
 */

class ChessActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_chess)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChessActivity::class.java))
        }
    }
}
