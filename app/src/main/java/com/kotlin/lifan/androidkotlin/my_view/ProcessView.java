package com.kotlin.lifan.androidkotlin.my_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author by LiFan
 * @date 2018/10/24
 */

public class ProcessView extends View implements MyViewImp{
    private float precent = 0.0f;
    private int wideSize;
    private int heightSize;
    private final TextPaint paint = new TextPaint();
//    private int barHeight = 6;
//    private


    public ProcessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        drawProcessBar(canvas);
        drawText(canvas);

    }

    private void drawText(Canvas canvas) {
        paint.setColor(frontColor);
        paint.setTextSize(dip2px(15));

        String string = String.valueOf((int) (precent*100 + 0.5f))+"%";
        float div = paint.measureText(string);

        float textX = ((BarEnd - BarStart) * precent + BarStart) * wideSize - div/2;
        float textY = heightSize * 60 / 130;
//        float textY = 0;
        canvas.drawText(string,textX,textY,paint);
//
    }

    private void drawProcessBar(Canvas canvas) {
        paint.setColor(backgroundColor);
        RectF rect = new RectF(wideSize * BarStart,
                heightSize * 86 / 130,
                BarEnd * wideSize,
                heightSize * 86 / 130 + dip2px(6));// 设置个新的长方形
        canvas.drawRoundRect(rect, 20, 20, paint);

        paint.setColor(frontColor);
        rect = new RectF(wideSize * BarStart,
                heightSize * 86 / 130,
                (BarStart + (BarEnd - BarStart) * precent) * wideSize,
                heightSize * 86 / 130 + dip2px(6));// 设置个新的长方形
        canvas.drawRoundRect(rect, 20, 20, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        wideSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        setMeasuredDimension(wideSize,heightSize);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float getSeekBarStart(){
        return BarStart * wideSize;
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void setProcess(float x) {
        if (x >= 0 && x <= 1) {
            precent = x;
            invalidate();
        }

    }

    @Override
    public void setValue(float x) {

    }

    @Override
    public void setMax(float max) {

    }

    @Override
    public void setMin(float min) {

    }
}
