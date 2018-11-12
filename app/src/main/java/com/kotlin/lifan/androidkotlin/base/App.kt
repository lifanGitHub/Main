package com.kotlin.lifan.androidkotlin.base

import android.app.Application
import com.kotlin.lifan.androidkotlin.data_base.DBActivity
import com.kotlin.lifan.androidkotlin.data_base.DataBase
import kotlin.properties.Delegates

/**
 * @author by LiFan
 * @date 2018/10/8
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        DataBase.getInstance().writeTabSys("System","APP启动")
    }
    companion object {
        @JvmStatic
        var instance : App by Delegates.notNull()
    }
}