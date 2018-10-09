package com.kotlin.lifan.androidkotlin.service.demo5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * User: LiFan
 * Date: 2018/1/10 
 * Time: 上午 10:04
 */

public class BroadcastService extends Service {

    private BroadcastReceiver receiver;

    private final String TAG = "BroadcastService";

    public BroadcastService() {
    }

    @Override
    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(TAG, "BroadcastService接收到了广播: " + intent.getStringExtra("K"));
            }
        };
        registerReceiver(receiver, intentFilter);
        Log.e(TAG, "BroadcastService注册了接收器");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.e(TAG, "BroadcastService取消注册接收器");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
