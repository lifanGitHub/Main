package com.kotlin.lifan.androidkotlin.demo_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kotlin.lifan.androidkotlin.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

/**
 * @author by LiFan
 * @date 2018/10/29
 */

public class ModeChoiceAc extends BaseActivity {
    public static void start(Context context){
        context.startActivity(new Intent(context,ModeChoiceAc.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_mode_choice);



    }
}
