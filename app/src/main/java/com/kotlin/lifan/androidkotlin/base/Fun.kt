package com.kotlin.lifan.androidkotlin.base

import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author by LiFan
 * @date 2018/10/8
 */

public fun showToast(string: String){
    Toast.makeText(App.instance,string,Toast.LENGTH_SHORT).show()
}

val dataFM = SimpleDateFormat("MM月dd日 HH:mm:ss")
fun formatData(long: Long): String {
    return dataFM.format(Date(long))
}
