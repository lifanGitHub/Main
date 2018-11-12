package com.kotlin.lifan.androidkotlin.data_base;
/**
 * User: LiFan
 * Date: 2018/1/31 
 * Time: 下午 3:02
 */

public class CMDUtil {


    public static final String CREATE_TAB1 =
            " CREATE TABLE IF NOT EXISTS " + "TAB" +
                    " (id integer primary key autoincrement, " +
                    " create_time long not null, " +
                    " content text not null ); ";

}
