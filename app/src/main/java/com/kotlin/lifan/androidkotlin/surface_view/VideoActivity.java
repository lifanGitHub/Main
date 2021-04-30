package com.kotlin.lifan.androidkotlin.surface_view;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import android.view.SurfaceView;

import com.kotlin.lifan.androidkotlin.base.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: LiFan
 * Date: 2018/1/22
 * Time: 下午 2:25
 */
public class VideoActivity extends BaseActivity {

    private Handler handler;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler();
            Looper.loop();
        }
    });


    @BindView(R.id.surface_layout)
    SurfaceView surfaceLayout;
    private MediaPlayer mediaPlayer;

    //    private SurfaceView surfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_video);
        ButterKnife.bind(this);

//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.lf);
//        mediaPlayer.setDisplay(surfaceLayout.getHolder());

    }

    @OnClick(R.id.start)
    public void onViewClicked() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setDisplay(surfaceLayout.getHolder());
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
