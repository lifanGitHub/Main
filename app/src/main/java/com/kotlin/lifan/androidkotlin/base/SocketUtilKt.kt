package com.kotlin.lifan.androidkotlin.base

import android.util.Log

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString


/**
 * @author by LiFan
 * @date 2018/12/20
 */
object SocketUtilKt {
    var socket: WebSocket? = null
    private fun log(s: String) {
        Log.i("lifanS", s)
    }

    fun init() {
        //创建Url和request
        val wsUrl = "ws://121.40.165.18:8800"
        val client = OkHttpClient.Builder()
                .build()
        val request = Request.Builder()
                .url(wsUrl)
                .build()
        //建立连接
        client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket?, response: Response?) {
                super.onOpen(webSocket, response)
                log("连接成功")
                socket = webSocket
            }

            override fun onMessage(webSocket: WebSocket?, text: String?) {
                super.onMessage(webSocket, text)
                log("消息" + text!!)
            }

            override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
                super.onMessage(webSocket, bytes)
            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                super.onClosing(webSocket, code, reason)
            }

            override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
                super.onClosed(webSocket, code, reason)
            }

            override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
                super.onFailure(webSocket, t, response)
                log("异常" + t!!.toString())
            }
        })


        //循环发送
        val dis = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe ({
                    socket?.send("发送消息$it")
                }, {
                    socket?.send("异常$it")
                })


    }


}
