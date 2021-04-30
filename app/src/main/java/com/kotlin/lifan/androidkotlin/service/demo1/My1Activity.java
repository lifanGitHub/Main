package com.kotlin.lifan.androidkotlin.service.demo1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kotlin.lifan.androidkotlin.R;


/**
 * User: LiFan
 * Date: 2018/1/2 
 * Time: 上午 10:34
 */

public class My1Activity extends AppCompatActivity {
    private boolean isBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(My1Activity.this, My1Service.class));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(My1Activity.this, My1Service.class));
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBind = true;
                bindService(new Intent(My1Activity.this, My12Service.class), connection, Context.BIND_AUTO_CREATE);
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


    }


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
