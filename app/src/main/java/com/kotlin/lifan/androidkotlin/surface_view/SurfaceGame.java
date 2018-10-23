package com.kotlin.lifan.androidkotlin.surface_view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SurfaceGame extends SurfaceView implements Callback {
    private Handler handler = new Handler();

    public SurfaceGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        startRxThread();
    }

    private void startRxThread() {
        Observable
//                .timer(3,TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .interval(50, TimeUnit.MILLISECONDS,Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {


                    }
                });


    }

    private void draw() {
        Canvas canvas = null;
        try {
            canvas = getHolder().lockCanvas();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }

    }

    private Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            while (true){
//                if (work) {
//                    draw();
//                    work = false;
//                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(width,width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        cleanCanvas();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void cleanCanvas(){
//        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
//        bitmap.eraseColor(BG_COLOR);
        Canvas canvas = getHolder().lockCanvas();
        if(canvas != null){
//            canvas.drawBitmap(bitmap, 0, 0, null);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }



}
