package com.example.test1

import android.content.Context
import android.widget.Toast

fun Context.Toastmessage(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}
