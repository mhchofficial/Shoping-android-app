package com.example.test1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test1.HomeActivity
import com.example.test1.R
import com.example.test1.SavedActivity
import com.example.test1.alert_support
import com.example.test1.databinding.FragmentProfileBinding
import com.example.test1.db.roomViewModel_users
import java.net.InterfaceAddress

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  var _bind: FragmentProfileBinding? = null

    lateinit var room: roomViewModel_users


    private val binding get() = _bind!!



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
        _bind = FragmentProfileBinding.inflate(inflater, container, false)

        room = ViewModelProvider(this)[roomViewModel_users::class.java]


        room.user.observe(viewLifecycleOwner, Observer {
            binding.fullname.text = it.name
            binding.textView.text = it.email
            Glide.with(this).load(it.img).into(binding.prof)
        })

        binding.exit.setOnClickListener {
            room.cleardata()
            val i = Intent(requireContext(), HomeActivity::class.java)
            activity?.startActivity(i)
            activity?.finish()
        }

        binding.support.setOnClickListener {
            alert_support(requireContext()).show()
        }

        binding.saved.setOnClickListener {
            val i = Intent(requireContext(), SavedActivity::class.java)
            activity?.startActivity(i)
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}