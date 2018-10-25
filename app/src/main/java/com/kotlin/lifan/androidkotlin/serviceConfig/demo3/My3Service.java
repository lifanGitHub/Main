package com.kotlin.lifan.androidkotlin.service.demo3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.kotlin.lifan.androidkotlin.service.EventService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:38
 */

public class My3Service extends Service {

    public static final String TAG = My3Service.class.getSimpleName();

    private MyBinder binder = new MyBinder();

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);

        //这里可以加入通知 成为前台Service

        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() executed");
        super.onUnbind(intent);
        return true;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() executed");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind() executed");
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEvent(EventService eventService){
        Log.i(TAG, Thread.currentThread().getName()+eventService.getS());

    }

    class MyBinder extends Binder {

        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }

    }
}
