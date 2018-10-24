package com.kotlin.lifan.androidkotlin.my_view;

import android.graphics.Color;

/**
 * @author by LiFan
 * @date 2018/10/24
 */

interface MyViewImp {
    int frontColor = Color.WHITE;
    int TextSize = 15;
    int backgroundColor = 0x66ffffff;

//    float BarStart = 0.133f;
//    float BarEnd = 0.867f;
    float BarStart = 0.08f;
    float BarEnd = 0.92f;
    float BarHeight = 6.0F;

    void setProcess(float x);

    void setValue(float x);

    void setMax(float max);

    void setMin(float min);
}
