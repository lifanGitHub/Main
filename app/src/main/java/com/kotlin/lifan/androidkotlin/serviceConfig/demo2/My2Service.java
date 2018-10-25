package com.kotlin.lifan.androidkotlin.service.demo2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:38
 */

public class My2Service extends Service {

    public static final String TAG = My2Service.class.getSimpleName();

    @Override
    public void onCreate() {
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
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() executed");
        return new MyBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind() executed");
    }

    public void showTip(){
        Log.d(TAG, "showTip");
    }

    class MyBinder extends Binder {
        /**
         * 获取Service的方法
         *
         * @return 返回PlayerService
         */
        public My2Service getService() {
            return My2Service.this;
        }
    }
}
