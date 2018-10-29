package com.kotlin.lifan.androidkotlin.demo_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.kotlin.lifan.androidkotlin.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by LiFan
 * @date 2018/10/26
 */

public class RoomModeViewX extends FrameLayout implements RoomModeItemViewX.ModeCheck{
    @BindView(R.id.view_group_1)
    ViewGroup viewGroup1;
    @BindView(R.id.view_group_2)
    ViewGroup viewGroup2;
    private final List<RoomModeItemViewX> viewList = new LinkedList<>();


    public RoomModeViewX(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_room_mode_new, this, true);
        ButterKnife.bind(RoomModeViewX.this);
        for (int i = 0; i< 4; i++){
            RoomModeItemViewX view1 = new RoomModeItemViewX(context,null);
            view1.init(R.drawable.mode0, R.drawable.mode0disable, R.drawable.mode_grey0, R.string.mode0,true, 0, RoomModeViewX.this);

            viewGroup1.addView(view1);
            viewList.add(view1);
        }
        for (int i = 0; i< 3; i++){
            RoomModeItemViewX view1 = new RoomModeItemViewX(context,null);
            view1.init(R.drawable.mode0, R.drawable.mode0disable, R.drawable.mode_grey0, R.string.mode0,false, 0, RoomModeViewX.this);
            view1.setCapEnable(false);
            viewGroup2.addView(view1);
            viewList.add(view1);
        }

    }

    @Override
    public void onCheck(RoomModeItemViewX view) {
        for (RoomModeItemViewX it : viewList){
            if (it != view){
                it.setChecked(false);
            }else {
//                it.setChecked(true);
            }
        }
    }

    public void updateUIFromPower(boolean b) {

    }

    public void enableAllView() {
//        TODO 准备废弃
    }

    public void setHexMode(Integer hexMode) {
        for (RoomModeItemViewX it : viewList){
            if (hexMode == it.hexMode && it.capEnable){
                it.setChecked(true);
            }else {
                it.setChecked(false);
            }
        }
    }

//    public void setCap(AirHardInfo info) {
//
//    }

//    public void initMode(int type, boolean haveXF){
//        if (haveXF && Key.Air.ALLControl == type){
//
//        }if (haveXF && Key.Air.RoomControl == type){
//
//        }if (haveXF && Key.Air.SingleControl == type){
//
//        }else if (!haveXF){
//
//        }
//    }

}
