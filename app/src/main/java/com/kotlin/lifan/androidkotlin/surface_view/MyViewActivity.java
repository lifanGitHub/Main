package com.kotlin.lifan.androidkotlin.surface_view;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.kotlin.lifan.androidkotlin.base.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

/**
 * User: LiFan
 * Date: 2018/1/29 
 * Time: 下午 3:24
 */

public class MyViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_view);
    }
}
