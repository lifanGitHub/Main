package com.kotlin.lifan.androidkotlin.data_base;

/**
 * @author by LiFan
 * @date 2018/11/12
 */

public class SYSModel {
    public long time;
    public String level,content;

    public SYSModel() {
    }

    public SYSModel(long time, String level, String content) {
        this.time = time;
        this.level = level;
        this.content = content;
    }
}
