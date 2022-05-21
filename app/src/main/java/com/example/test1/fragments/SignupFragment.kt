package com.example.test1.fragments

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.test1.R
import com.example.test1.Toastmessage
import com.example.test1.databinding.FragmentSignupBinding
import com.example.test1.viewmodels.ModelView_signup
import com.example.test1.viewmodels.Viewinterface_signup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment(), Viewinterface_signup {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var bind: FragmentSignupBinding
    lateinit var viewmodel: ModelView_signup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind =  DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        viewmodel = ViewModelProvider(this)[ModelView_signup::class.java]
        bind.viewmodel = viewmodel
        viewmodel.interfacecheck = this

        //we usually send data to webserver and save to db
        //but my web server is a simple json file so i cant do that
        //but is simple and you can do it your self
        //so basicly we skip this part and start activity




        return bind.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun error(msg: String) {
        activity?.Toastmessage("we have error about $msg")
    }

    override fun success(email: String, password: String, nicname: String) {
        //skip sending data to webserver
        activity?.Toastmessage("successful, please login")

    }


}