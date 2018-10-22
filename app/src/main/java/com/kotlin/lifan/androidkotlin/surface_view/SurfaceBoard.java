package com.kotlin.lifan.androidkotlin.surface_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

import com.kotlin.lifan.androidkotlin.R;

public class SurfaceBoard extends SurfaceView implements Callback {
    public static final int BG_COLOR= Color.WHITE;
    private Bitmap bitmapCache;
    private volatile boolean work;
    private Bitmap bitmap;
    private Path path;
    private Paint paint = new Paint();
    private int width,height;
    private MotionEvent motionEvent;
    private volatile int cleanScreen;
    public static final Long SleepTime = 10L;

    public SurfaceBoard(Context context) {
        super(context);
    }

    public void init() {
        path = new Path();
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        setDefaultPaint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rose);
        getHolder().addCallback(this);

        setPathMode();
//        setOnTouchListener();
        new Thread(drawRunnable).start();
    }

    private void setPathMode() {
        setOnTouchListener(touchListenerPath);
    }

    private void setClickMode(){

    }

    private void draw() {
//        long n = System.currentTimeMillis();
        Canvas canvas = null;
        try {
            canvas = getHolder().lockCanvas();
            canvas.drawPath(path, paint);
            if (motionEvent != null){
                canvas.drawBitmap(bitmap,motionEvent.getX(),motionEvent.getY(),paint);
                motionEvent = null;
            }
            if (cleanScreen > 0){
                canvas.drawColor(BG_COLOR);
                cleanScreen--;
            }
        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
//        long nn = System.currentTimeMillis();
//        Log.e("","消耗时间"+(nn-n));

    }

    private Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                if (work) {
                    draw();
                    work = false;
                }
                try {
                    Thread.sleep(SleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public void setEraserPaint(){
        setOnTouchListener(touchListenerPath);
        paint = new Paint();
        path = new Path();
        paint.setColor(BG_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
    }

    public void setDefaultPaint(){
        setOnTouchListener(touchListenerPath);
        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    public void clearScreen() {
        cleanScreen = 2;
        path = new Path();
        work = true;
    }

    public void setPicPaint() {
        setOnTouchListener(touchListenerPoint);
    }

    private OnTouchListener touchListenerPath = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            work = true;
            int x = (int) event.getX();
            int y = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x, y);
                    break;
                case MotionEvent.ACTION_UP:
//                        path.lineTo(x, y);
                    break;
            }
            return true;
        }
    };

    private OnTouchListener touchListenerPoint = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (work){
                //正在工作
                return false;
            }else {
                motionEvent = event;
                work = true;
            }

            return false;
        }
    };




    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cleanCanvas();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void cleanCanvas(){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(BG_COLOR);
        Canvas canvas = getHolder().lockCanvas();
        if(canvas != null){
//            canvas.drawBitmap(bitmap, 0, 0, null);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }



}
