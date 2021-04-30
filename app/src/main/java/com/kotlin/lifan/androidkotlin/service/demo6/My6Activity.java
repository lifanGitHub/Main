package com.kotlin.lifan.androidkotlin.service.demo6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kotlin.lifan.androidkotlin.IMyAidlInterface;
import com.kotlin.lifan.androidkotlin.R;

/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:34
 */

public class My6Activity extends AppCompatActivity {
    private boolean isBind;
    private static final String TAG = "BinderSimple";
    private int id;
    private IMyAidlInterface aidl;

    private boolean mIsBound;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(My6Activity.this, My6Service.class));
            }
        });
        findViewById(R.id.button).setEnabled(false);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(My6Activity.this, My6Service.class));
            }
        });
        findViewById(R.id.button2).setEnabled(false);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "[ClientActivity] bindRemoteService");
                Intent intent = new Intent(My6Activity.this, My6Service.class);
                intent.setAction(My6Service.class.getName());
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

                mIsBound = true;
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mIsBound){
                    return;
                }
                Log.i(TAG, "[ClientActivity] unbindRemoteService ==>");
                unbindService(mConnection);
                mIsBound = false;
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new EventService("ToService"));
                try {
                    android.os.Process.killProcess(aidl.getPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(My6Activity.this, "Kill", Toast.LENGTH_SHORT).show();

            }
        });


    }


    /**
     * 用语监控远程服务连接的状态
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            aidl = IMyAidlInterface.Stub.asInterface(service);
            String pidInfo = null;
            try {
                id = aidl.getPid();
//                MyData myData = aidl.getMyData();
                pidInfo = "pid="+ aidl.getPid() ;
//                        ", data1 = "+ myData.getData1() +
//                        ", data2="+ myData.getData2();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "[ClientActivity] onServiceConnected  "+pidInfo);
            Toast.makeText(My6Activity.this, "连接成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "[ClientActivity] onServiceDisconnected");
            aidl = null;
            Toast.makeText(My6Activity.this, "解除连接", Toast.LENGTH_SHORT).show();
        }
    };

}
