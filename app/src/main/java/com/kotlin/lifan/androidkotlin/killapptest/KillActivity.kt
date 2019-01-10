package com.kotlin.lifan.androidkotlin.killapptest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.ac_kill.*;
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.base.App
import com.kotlin.lifan.androidkotlin.base.BaseActivity

class KillActivity : BaseActivity() {
    companion object {
        const val hint = "使用以下命令杀死App\n" +
                "adb shell am force-stop packageName\n" +
                "然后后台唤醒APP查看\n" +
                "与《不建议再application存储数据》不符合"

        fun start(context: Context){
            context.startActivity(Intent(context,KillActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_kill)
        buttonView.text = "第${App.killTestIndex}次打开"
        hintView.text = hint
        buttonView.setOnClickListener {
            start(this)
        }

    }

}
