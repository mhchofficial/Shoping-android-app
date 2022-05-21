package com.example.test1.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel

class ModelView_signup: ViewModel() {

    var email: String ?= null
    var password: String ?= null
    var nicname: String ?= null

    var interfacecheck: Viewinterface_signup?=null




    fun click(view: View){
        if (!email.isNullOrEmpty() and !password.isNullOrEmpty() and !nicname.isNullOrEmpty()) {
            interfacecheck?.success(email.toString(), password.toString(), nicname.toString())


        }
        else if (email.isNullOrEmpty()!!){
            interfacecheck?.error("email")
        }
        else if (password.isNullOrEmpty()!!){
            interfacecheck?.error("password")
        }



    }
}