package com.example.test1.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel

class ModelView_login: ViewModel() {

    var email: String ?= null
    var password: String ?= null
    var interfacecheck: Viewinterface_login?=null




    fun click(view: View){
        if (!email.isNullOrEmpty() and !password.isNullOrEmpty()) {
            interfacecheck?.success(email.toString(), password.toString())


        }
        else if (email.isNullOrEmpty()!!){
            interfacecheck?.error("email")
        }
        else if (password.isNullOrEmpty()!!){
            interfacecheck?.error("password")
        }



    }
}