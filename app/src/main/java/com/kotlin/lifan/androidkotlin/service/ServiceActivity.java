package com.kotlin.lifan.androidkotlin.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kotlin.lifan.androidkotlin.R;
import com.kotlin.lifan.androidkotlin.item1.RecyclerActivity;
import com.kotlin.lifan.androidkotlin.service.demo1.My1Activity;
import com.kotlin.lifan.androidkotlin.service.demo2.My2Activity;
import com.kotlin.lifan.androidkotlin.service.demo3.My3Activity;
import com.kotlin.lifan.androidkotlin.service.demo4.My4Activity;
import com.kotlin.lifan.androidkotlin.service.demo5.My5Activity;
import com.kotlin.lifan.androidkotlin.service.demo6.My6Activity;

public class ServiceActivity extends AppCompatActivity {

    public static void start(Context context){
        context.startActivity(new Intent(context,ServiceActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_service);

//        startActivity(new Intent(this,My1Activity.class));
//        startActivity(new Intent(this,My2Activity.class));
//        startActivity(new Intent(this,My3Activity.class));
//        startActivity(new Intent(this,My4Activity.class));
//        startActivity(new Intent(this,My5Activity.class));
        startActivity(new Intent(this,My6Activity.class));


    }
}
