package com.kotlin.lifan.androidkotlin.base

import android.app.Application
import kotlin.properties.Delegates

/**
 * @author by LiFan
 * @date 2018/10/8
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        var instance : App by Delegates.notNull()
    }
}