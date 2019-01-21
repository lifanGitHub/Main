package com.kotlin.lifan.androidkotlin.base

import android.app.Application
import com.kotlin.lifan.androidkotlin.data_base.DataBase
import com.kotlin.lifan.androidkotlin.web_socket_test.SocketJava
import com.kotlin.lifan.androidkotlin.web_socket_test.SocketUtilKt
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

        SocketJava.init()
    }
    companion object {
        @JvmStatic
        var killTestIndex = 0
        get() {
            field++
            return field
        }
        @JvmStatic
        var instance : App by Delegates.notNull()
    }
}