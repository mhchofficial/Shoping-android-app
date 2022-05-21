package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test1.databinding.ActivityHomeBinding
import com.example.test1.db.roomViewModel_users
import com.example.test1.fragments.CartFragment
import com.example.test1.fragments.HomeFragment
import com.example.test1.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var room: roomViewModel_users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        room = ViewModelProvider(this)[roomViewModel_users::class.java]

        transfragment(HomeFragment())
        val nav = binding.navBtn
        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fr_home -> {
                    transfragment(HomeFragment())
                    true
                }

                R.id.fr_cart -> {
                    transfragment(CartFragment())
                    true
                }
                R.id.fr_profile -> {
                    check_db()
                    //transfragment(ProfileFragment())
                    true
                }


                else -> {
                    transfragment(HomeFragment())
                    true
                }
            }
        }

    }


    private fun transfragment(fragment: Fragment) {
        val f = supportFragmentManager.beginTransaction()
        f.replace(R.id.fl_lay, fragment)
        f.commit()

    }

    private fun check_db(){

        room.user.observe(this, Observer {
            if (it != null) {
                transfragment(ProfileFragment())
            }else{
                val i = Intent(this, UserLogin::class.java)
                startActivity(i)
                finish()
            }

        })


    }

}