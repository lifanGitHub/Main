package com.kotlin.lifan.androidkotlin

import android.widget.Toast

/**
 * @author by LiFan
 * @date 2018/10/8
 */

public fun showToast(string: String){
    Toast.makeText(App.instance,string,Toast.LENGTH_SHORT).show()
}
