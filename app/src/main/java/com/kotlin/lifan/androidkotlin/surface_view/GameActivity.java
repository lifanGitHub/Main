package com.kotlin.lifan.androidkotlin.surface_view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kotlin.lifan.androidkotlin.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

/**
 * User: LiFan
 * Date: 2018/1/23 
 * Time: 下午 2:32
 */

public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_game);
    }
}
