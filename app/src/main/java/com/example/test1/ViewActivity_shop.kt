package com.example.test1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.test1.adapters.RecyclerAdapter_comments
import com.example.test1.databinding.ActivityViewShopBinding
import com.example.test1.db.*
import com.example.test1.viewmodels.ViewModel_add_comment
import com.example.test1.viewmodels.ViewModel_comments
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ViewActivity_shop : AppCompatActivity() {
    private lateinit var bind: ActivityViewShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityViewShopBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val room: roomViewModel_cart = ViewModelProvider(this)[roomViewModel_cart::class.java]
        val user: roomViewModel_users = ViewModelProvider(this)[roomViewModel_users::class.java]


        //best way is get id  and send to webserver and get detail
        //but we use data that sending from recycler

        val title = intent.getStringExtra("title").toString()
        val price = intent.getStringExtra("price").toString()
        val img = intent.getStringExtra("img").toString()


        Glide.with(this).load(img).into(bind.shapeableImageView);

        reload_comments()

        bind.title.text = title
        bind.price.text = price


        bind.spinKit.visibility = View.GONE

        val send = "$title\n   only with price$price"

        bind.share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, send);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
        }


        bind.addToCart.setOnClickListener {
            val t = getDateTime()
            val data = RoomModel_cart(0, title, price, t, false)
            room.insert(data)
            Toastmessage("added to cart")
        }


        var username: String = "guest"
        user.user.observe(this, Observer {
            if (it != null) {
                username = it.name.toString()

            }
        })

        bind.addCom.setOnClickListener {
            val com = bind.com.text.toString()
            val add = ViewModelProvider(this)[ViewModel_add_comment::class.java]
            add.call(this, username, com)
            add.response.observe(this, Observer {
                if (it.status) {
                    Toastmessage("comment added")
                    reload_comments()
                }
            })

        }


    }
    private fun reload_comments(){
        val call = ViewModelProvider(this@ViewActivity_shop)[ViewModel_comments::class.java]
        call.call(this)
        call.response.observe(this@ViewActivity_shop, Observer {
            bind.recyclerView.apply {
                this.adapter = RecyclerAdapter_comments(it)
                this.layoutManager = LinearLayoutManager(this@ViewActivity_shop)
            }
        })
    }
    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()
        return dateFormat.format(date)
    }


}