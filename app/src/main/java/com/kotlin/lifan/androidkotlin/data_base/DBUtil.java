package com.kotlin.lifan.androidkotlin.data_base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: LiFan
 * Date: 2018/1/31 
 * Time: 下午 2:52
 */

public class DBUtil extends SQLiteOpenHelper {
    public static final String NAME = "LF_DB";
    public static final int VERSION = 1;

    public DBUtil(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CMDUtil.CREATE_TAB1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
