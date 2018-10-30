package com.kotlin.lifan.androidkotlin.demo_list;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.kotlin.lifan.androidkotlin.R;
import com.kotlin.lifan.androidkotlin.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by LiFan
 * @date 2018/5/7 0007
 */

public class RoomModeItemViewX extends FrameLayout {
    private ModeCheck listener;
    private boolean lineEnable;
    //是否被选中
    public boolean isChecked;
    private int index = 0;
    public boolean capEnable = true;
    private @DrawableRes
    int defaultRes;
    private @DrawableRes
    int checkRes;
    private @DrawableRes
    int disableRes;
    public int hexMode = -1;

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.default_image)
    ImageView defaultImage;
    @BindView(R.id.check_image)
    ImageView checkImage;

    public RoomModeItemViewX(@NonNull Context context) {
        super(context);
    }

    public RoomModeItemViewX init(final @DrawableRes int defaultRes,
                                  final @DrawableRes int checkRes,
                                  final @DrawableRes int disableRes,
                                  final @StringRes int textRes,
                                  final boolean haveLine,
                                  final int hexMode,
                                  final @NonNull ModeCheck listener){
        this.listener = listener;
        this.lineEnable = haveLine;
        this.defaultRes = defaultRes;
        this.checkRes = checkRes;
        this.disableRes = disableRes;
        this.hexMode = hexMode;
        textView.setText(textRes);
        imageView.setImageResource(defaultRes);
        defaultImage.setVisibility(haveLine ? VISIBLE : GONE);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (isChecked && event.getAction() == MotionEvent.ACTION_DOWN){
//                    checkImage.setVisibility(VISIBLE);
//                    return false;
//                }
                if (!capEnable) {
                    return false;
                }else if (isChecked){
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    imageView.setImageResource(checkRes);
                    checkImage.setVisibility(VISIBLE);
                    textView.setTextColor(getContext().getResources().getColor(R.color.base_disable));
//                    setLine(true);
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    isChecked = ViewUtil.Companion.isInTouchRect(RoomModeItemViewX.this,event);
                    imageView.setImageResource(isChecked ? checkRes : defaultRes);
                    textView.setTextColor(isChecked ?
                            getContext().getResources().getColor(R.color.base_disable) :
                            getContext().getResources().getColor(R.color.white));
//                    setLine(isChecked);
                    checkImage.setVisibility(GONE);
                    defaultImage.setVisibility(lineEnable ? VISIBLE : GONE);
                    if (isChecked) {
                        listener.onCheck(RoomModeItemViewX.this);
                    }
                }else if (event.getAction() == MotionEvent.ACTION_MOVE){
                    boolean state = ViewUtil.Companion.isInTouchRect(RoomModeItemViewX.this,event);
                    imageView.setImageResource(state ? checkRes : defaultRes);
                    textView.setTextColor(state ?
                            getContext().getResources().getColor(R.color.base_disable) :
                            getContext().getResources().getColor(R.color.white));
                    checkImage.setVisibility(state ? VISIBLE : GONE);
//                    setLine(state);
                }

                return true;
            }
        });
        return this;
    }


    public RoomModeItemViewX(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.view_room_mode_item, this, true);
        ButterKnife.bind(this);
        setLayoutParams(params);

    }

    private static final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);

    public void setChecked(boolean b) {
        if (capEnable) {
            isChecked = b;
            imageView.setImageResource(isChecked ? checkRes : defaultRes);
            textView.setTextColor(isChecked ?
                    getContext().getResources().getColor(R.color.base_disable) :
                    getContext().getResources().getColor(R.color.white));
        }

    }

    public RoomModeItemViewX setCapEnable(boolean b) {
        isChecked = false;
        capEnable = false;
        imageView.setImageResource(disableRes);
        textView.setTextColor(getContext().getResources().getColor(R.color.grey_7f));
        return this;
    }

    public interface ModeCheck{
        void onCheck(RoomModeItemViewX view);
    }
}


