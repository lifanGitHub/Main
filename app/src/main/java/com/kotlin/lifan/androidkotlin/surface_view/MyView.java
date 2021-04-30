package com.kotlin.lifan.androidkotlin.surface_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * User: LiFan
 * Date: 2018/1/29 
 * Time: 下午 3:06
 */

public class MyView extends View {
    private MotionEvent motionEvent;
    private Canvas canvas;
    private Path path = new Path();
    private Paint paint = new Paint();
    private boolean firstDraw = true;

    public MyView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        path.moveTo(x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        path.lineTo(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
//                        path.lineTo(x, y);

                        break;
                }
                return true;
            }
        });
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (firstDraw){
            firstDraw = false;
            Bitmap mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
            this.canvas = new Canvas(mBitmap);
            //抗锯齿
            this.canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            //背景色
            this.canvas.drawColor(Color.WHITE);
            return;
        }
        canvas.drawPath(path,paint);

    }




    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
