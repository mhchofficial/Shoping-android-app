package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.adapters.RecyclerAdapter_saved
import com.example.test1.databinding.ActivitySavedBinding
import com.example.test1.db.roomViewModel_save

class SavedActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySavedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val room : roomViewModel_save = ViewModelProvider(this)[roomViewModel_save::class.java]

        room.save.observe(this, Observer {
            Log.e("sise", it.size.toString())
            if (it.isNullOrEmpty()){
                bind.warn.visibility = View.VISIBLE
                bind.savedRec.visibility = View.GONE
            }else{
                bind.savedRec.apply {
                    this.adapter = RecyclerAdapter_saved(it)
                    this.layoutManager = LinearLayoutManager(this@SavedActivity)
                }
            }

        })
    }
}