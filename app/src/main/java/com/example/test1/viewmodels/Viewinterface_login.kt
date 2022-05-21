package com.example.test1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface Viewinterface_login {

    fun error(msg: String)

    fun success(email: String, password: String)



}
