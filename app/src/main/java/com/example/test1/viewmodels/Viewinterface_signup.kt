package com.example.test1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface Viewinterface_signup {

    fun error(msg: String)

    fun success(email: String, password: String, nicname: String,)



}
