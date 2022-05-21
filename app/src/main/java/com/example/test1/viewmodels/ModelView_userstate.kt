package com.example.test1.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel

class ModelView_userstate: ViewModel() {

    var interfacecheck: Viewinterface?=null

    fun login(view: View){
        interfacecheck?.success("login")
    }

    fun signup(view: View){
        interfacecheck?.success("signup")

    }
}