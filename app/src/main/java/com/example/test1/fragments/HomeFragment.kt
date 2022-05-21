package com.example.test1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test1.R
import com.example.test1.adapters.PageAdapter
import com.example.test1.adapters.PageAdapter_pru
import com.example.test1.adapters.PageAdapter_recent
import com.example.test1.databinding.FragmentHomeBinding
import com.example.test1.db.roomViewModel_top
import com.example.test1.viewmodels.ViewModel_pru
import com.example.test1.viewmodels.ViewModel_recent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding:  FragmentHomeBinding? = null
    private lateinit var room: roomViewModel_top
    private lateinit var recent: ViewModel_recent
    private lateinit var pru: ViewModel_pru



    private val binding get() = _binding!!



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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        room = ViewModelProvider(this)[roomViewModel_top::class.java]
        recent = ViewModelProvider(this)[ViewModel_recent::class.java]
        pru = ViewModelProvider(this)[ViewModel_pru::class.java]


        room.top.observe(viewLifecycleOwner, Observer {

            val viewpager = binding.viewPager
            viewpager.apply {
                this.adapter = PageAdapter(it)
                this.clipToPadding = false
                this.pageMargin = 100
                this.setPadding(80, 0, 5, 0)
                this.currentItem = 2
            }
            binding.springDotsIndicator.setViewPager(viewpager)

        })


        recent.tops(requireContext())
        recent.response.observe(viewLifecycleOwner, Observer {

            val rec_pager = binding.viewPager2
            rec_pager.apply {
                this.adapter = PageAdapter_recent(it)
                this.clipToPadding = false
                this.pageMargin = 30
                this.setPadding(40, 40, 10, 40)
                this.currentItem = 1
            }

        })


        pru.tops(requireContext())
        pru.response.observe(viewLifecycleOwner, Observer {
            val bt_pager = binding.viewPager3
            bt_pager.apply {
                this.adapter = PageAdapter_pru(it)
                this.clipToPadding = false
                this.pageMargin = 30
                this.setPadding(40, 0, 10, 0)
                this.currentItem = 1
            }
        })
        val view = binding.root



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}