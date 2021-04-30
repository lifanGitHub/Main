package com.kotlin.lifan.androidkotlin.my_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.kotlin.lifan.androidkotlin.base.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author by LiFan
 * @date 2018/10/24
 */

public class SeekBarAc extends BaseActivity {
    private Disposable disposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_seekbar);
        final ProcessView processView = findViewById(R.id.my_view);

        disposable = Observable
                .interval(100, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        float x = aLong * 0.1f / 1000 / 10;
                        processView.setProcess(x);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context,SeekBarAc.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null){
            disposable.dispose();
        }
    }
}
