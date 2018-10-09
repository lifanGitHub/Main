package com.kotlin.lifan.androidkotlin.service.demo4;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kotlin.lifan.androidkotlin.MyAIDLService;
import com.kotlin.lifan.androidkotlin.R;


/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:34
 */

public class My4Activity extends AppCompatActivity {
    public static final String Key = "KEY";
    private boolean isBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(My4Activity.this, My4Service.class));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(My4Activity.this, My4Service.class));
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBind = true;
                Intent intent = new Intent(My4Activity.this,My4Service.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind) {
                    isBind = false;
                    unbindService(connection);
                }else {
                    Toast.makeText(getApplicationContext(),"不可重复解绑", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My4Activity.this, My4Service.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("K","From AC");
                bundle.putString("S","From AC");
                intent.putExtras(bundle);
                startService(new Intent(My4Activity.this, My4Service.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,My4Service.class));
    }

    private MyAIDLService myAIDLService;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }
    };

}
