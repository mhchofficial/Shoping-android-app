package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test1.db.RoomModel_top
import com.example.test1.db.roomViewModel_top
import com.example.test1.viewmodels.ViewModel_top

class MainActivity : AppCompatActivity() {
    lateinit var modelTop: ViewModel_top
    lateinit var room: roomViewModel_top
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modelTop = ViewModelProvider(this)[ViewModel_top::class.java]
        room = ViewModelProvider(this)[roomViewModel_top::class.java]

        room.cleardata()
        modelTop.tops(this)
        modelTop.response.observe(this, Observer {

            for (item in it){
                val data = RoomModel_top(0, item.title, item.body, item.img_url)
                room.insert(data)
            }

            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()

        })


        //we can add a api for checking new update for application




    }
}