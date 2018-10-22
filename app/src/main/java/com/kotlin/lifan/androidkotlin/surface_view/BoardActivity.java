package com.kotlin.lifan.androidkotlin.surface_view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.kotlin.lifan.androidkotlin.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: LiFan
 * Date: 2018/1/19
 * Time: 上午 10:41
 */

public class BoardActivity extends BaseActivity {
    @BindView(R.id.surface_layout)
    FrameLayout surfaceLayout;
    private SurfaceBoard surfaceBoard;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_board);
        ButterKnife.bind(this);

        surfaceBoard = new SurfaceBoard(this);
        surfaceBoard.init();
        surfaceLayout.addView(surfaceBoard);

    }

    @OnClick({R.id.write, R.id.text, R.id.pic, R.id.eraser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.write:
                surfaceBoard.setDefaultPaint();
                break;
            case R.id.text:
                surfaceBoard.clearScreen();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        surfaceBoard.clearScreen();
                    }
                },50);
                break;
            case R.id.pic:
                surfaceBoard.setPicPaint();
                break;
            case R.id.eraser:
                surfaceBoard.setEraserPaint();
                break;
        }
    }
}
