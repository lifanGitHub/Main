package com.kotlin.lifan.androidkotlin.service.demo4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:38
 */

public class My4Service extends Service {

    public static final String TAG = My4Service.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed" + " UID : " + Process.myUid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int b = super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "onStartCommand() executed");

        String s = null;
        try {
            if (intent == null){
                Log.d(TAG, "int null");
            }
            if (intent.getExtras() == null){
                Log.d(TAG, "ex null");
            }
            s = intent.getStringExtra("K");
        } catch (Exception e) {
            Log.d(TAG, "ex null");
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(s)){
            Log.d(TAG, ""+s);
        }

        return b;
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
        return null;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind() executed");
    }


}
