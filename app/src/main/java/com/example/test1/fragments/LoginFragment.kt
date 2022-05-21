package com.example.test1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test1.HomeActivity
import com.example.test1.R
import com.example.test1.databinding.FragmentLoginBinding
import com.example.test1.db.RoomModel_users
import com.example.test1.db.roomViewModel_users
import com.example.test1.viewmodels.ModelView_login
import com.example.test1.viewmodels.ViewModel_login
import com.example.test1.viewmodels.Viewinterface_login

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), Viewinterface_login {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var bind: FragmentLoginBinding
    private lateinit var room: roomViewModel_users
    private lateinit var viewmodel: ModelView_login
    private lateinit var apicall : ViewModel_login



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
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewmodel = ViewModelProvider(this)[ModelView_login::class.java]
        bind.viewmodel = viewmodel
        viewmodel.interfacecheck = this
        room = ViewModelProvider(this)[roomViewModel_users::class.java]

        apicall = ViewModelProvider(this)[ViewModel_login::class.java]


        return bind.root

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun error(msg: String) {
        TODO("Not yet implemented")
    }

    override fun success(email: String, password: String) {
        apicall.call(requireContext())
        apicall.response.observe(viewLifecycleOwner, Observer {
            val data = RoomModel_users(0,it[0].fullname, it[0].img, it[0].email)
            room.insert(data)
            val i = Intent(requireContext(), HomeActivity::class.java)
            activity?.startActivity(i)
            activity?.finish()
        })

    }
}