package com.kotlin.lifan.androidkotlin.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.CheckResult;
import android.util.Log;

import com.kotlin.lifan.androidkotlin.base.App;

import java.util.LinkedList;
import java.util.List;

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

    public static DataBase getInstance(){
        if (instance == null){
            synchronized (DataBase.class){
                if (instance == null)
                    instance = new DataBase(App.getInstance());
            }
        }
        return instance;
    }

    public void writeTabSys(String level, String text){
        final SQLiteDatabase sql = dbUtil.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("create_time",System.currentTimeMillis());
        values.put("content",text);
        values.put("level",level);
        sql.replaceOrThrow("TAB",null,values);
        sql.close();
    }

    @CheckResult
    public List<SYSModel> readTabSys(){
        String sqlCmd = " select * from " + " TAB ; ";
        Cursor cursor = dbUtil.getWritableDatabase().rawQuery(sqlCmd, null);
        List<SYSModel> list = new LinkedList<>();
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            SYSModel model = new SYSModel();
            model.time = cursor.getLong(cursor.getColumnIndexOrThrow("create_time"));
            model.level = cursor.getString(cursor.getColumnIndexOrThrow("level"));
            model.content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            list.add(model);
        }
        cursor.close();
        return list;
    }


}
