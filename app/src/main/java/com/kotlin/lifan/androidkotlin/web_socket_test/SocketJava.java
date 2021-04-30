package com.kotlin.lifan.androidkotlin.web_socket_test;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


/**
 * @author by LiFan
 * @date 2018/12/20
 */
public class SocketJava {
    public static WebSocket mWebSocket;
    private static void log(String s){
        System.out.println("webs:"+s);
    }
    private static Disposable disposable;
    public static void init(){
        log("开始");
        String wsUrl = "ws://184.170.223.3:8070/webSocket";
//        String wsUrl = "ws://172.29.12.67:8070/webSocket";
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                log("连接成功");
                mWebSocket = webSocket;
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                log("消息" + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                log("异常"+t.toString());
            }
        });


        disposable =
        Observable.interval(1,TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (mWebSocket!=null) {
                            mWebSocket.send("发送消息" + aLong);
//                            disposable.dispose();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log("异常"+throwable.toString());
                        disposable.dispose();
                    }
                });
    }

    public static void main(String[] args) {
        SocketJava.init();
    }
}
