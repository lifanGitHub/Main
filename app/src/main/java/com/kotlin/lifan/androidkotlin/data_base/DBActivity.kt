package com.kotlin.lifan.androidkotlin.data_base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.base.BaseActivity
import com.kotlin.lifan.androidkotlin.base.formatData
import kotlinx.android.synthetic.main.ac_db.*

/**
 * @author by LiFan
 * @date 2018/11/12
 */

class DBActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_db)

        DataBase.getInstance().writeTabSys("用户","查看数据库日志")

        val list = DataBase.getInstance().readTabSys()
        for (it in list.size -1 downTo 0){
            val sysModel = list[it]
            val str: String = "时间:${formatData(sysModel.time)} \n来自:${sysModel.level} 内容:${sysModel.content}\n-----------------------------------------\n" +
                    ""
            dbView.append(str)
        }

    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DBActivity::class.java))
        }
    }

}
