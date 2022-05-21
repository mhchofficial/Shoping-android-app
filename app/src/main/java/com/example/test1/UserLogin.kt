package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test1.databinding.ActivityUserLoginBinding
import com.example.test1.fragments.LoginFragment
import com.example.test1.fragments.SignupFragment
import com.example.test1.viewmodels.ModelView_userstate
import com.example.test1.viewmodels.Viewinterface

class UserLogin : AppCompatActivity(), Viewinterface {
    private lateinit var bind: ActivityUserLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_user_login)
        val viewmodel: ModelView_userstate =
            ViewModelProvider(this)[ModelView_userstate::class.java]
        bind.sts = viewmodel
        viewmodel.interfacecheck = this
        transfragment(LoginFragment())
    }

    private fun transfragment(fragment: Fragment) {
        val f = supportFragmentManager.beginTransaction()
        f.replace(R.id.fr_lay_state, fragment)
        f.commit()

    }

    override fun error(msg: String) {
        //
    }

    override fun success(msg: String) {
        if (msg=="login"){
            transfragment(LoginFragment())
        }
        if (msg == "signup"){
            transfragment(SignupFragment())
        }
    }
}