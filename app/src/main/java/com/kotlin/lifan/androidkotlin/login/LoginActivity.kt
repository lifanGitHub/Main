package com.kotlin.lifan.androidkotlin.login

import android.os.Bundle
import com.kotlin.lifan.androidkotlin.R
import com.kotlin.lifan.androidkotlin.base.BaseActivity
import com.kotlin.lifan.androidkotlin.base.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_login2.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    var dispose: Disposable ?= null
    var add = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        dispose = Observable
                .interval(100, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ aLong ->
                    val x = aLong!! * 0.1f / 1000f / 10f * 4
                    processView.setProcess(x)
                }) { }
        textHintView.setOnClickListener{
            add++
            if (add == 2){
                MainActivity.start(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose?.isDisposed
    }

}
