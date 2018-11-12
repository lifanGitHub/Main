package com.kotlin.lifan.androidkotlin.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.CheckResult;
import android.util.Log;

/**
 * User: LiFan
 * Date: 2018/1/31 
 * Time: 下午 3:10
 */

public class DataBase {
    private DBUtil dbUtil;
    private volatile static DataBase instance;


    private DataBase(Context context){
        dbUtil = new DBUtil(context.getApplicationContext());
    }

    public static DataBase getInstance(Context context){
        if (instance == null){
            synchronized (DataBase.class){
                if (instance == null)
                    instance = new DataBase(context);
            }
        }
        return instance;
    }

    public void writeTab1(long time, String text, int id){
        final SQLiteDatabase sql = dbUtil.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("create_time",time);
        Log.i("qwer",""+id);
        values.put("id",id);
        values.put("content",text);
        sql.replaceOrThrow("TAB",null,values);
        sql.close();
    }

    @CheckResult
//    @Deprecated
    public String readTab1(int id){
        String sqlCmd = " select * from " + " TAB " + " where id = " + id + " ; ";
        Cursor cursor = dbUtil.getWritableDatabase().rawQuery(sqlCmd, null);
        long time = 0;
        String content = "";
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            time = cursor.getInt(cursor.getColumnIndexOrThrow("create_time"));
            content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
        }
        cursor.close();
        return content + time;
    }


}
