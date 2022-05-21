package com.example.test1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.test1.adapters.RecyclerAdapter_comments
import com.example.test1.databinding.ActivityViewPostBinding
import com.example.test1.db.RoomModel_save
import com.example.test1.db.roomViewModel_save
import com.example.test1.db.roomViewModel_users
import com.example.test1.viewmodels.ViewModel_add_comment
import com.example.test1.viewmodels.ViewModel_comments

class ViewActivity_post : AppCompatActivity() {
    private lateinit var bind: ActivityViewPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val room: roomViewModel_save = ViewModelProvider(this)[roomViewModel_save::class.java]
        val user: roomViewModel_users = ViewModelProvider(this)[roomViewModel_users::class.java]


        //best way is get id  and send to webserver and get detail
        //but we use data that sending from recycler

        val title = intent.getStringExtra("title").toString()
        val body = intent.getStringExtra("body").toString()
        val img = intent.getStringExtra("img").toString()


        Glide.with(this).load(img).into(bind.shapeableImageView);

        bind.textView4.text = title
        bind.textView5.text = body

        val send = title + "\n" + body


        room.search(title.toString()).observe(this, Observer {
            Log.e("sis", it.size.toString())
            if(!it.isNullOrEmpty()){
                bind.save1.visibility = View.GONE
                bind.save2.visibility = View.VISIBLE
            }
        })


        bind.share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, send);
            startActivity(Intent.createChooser(shareIntent,getString(R.string.send_to)))
        }

        bind.save1.setOnClickListener {
            val data = RoomModel_save(0, title, body, img)
            room.insert(data)
            bind.save1.visibility = View.GONE
            bind.save2.visibility = View.VISIBLE
        }

        bind.save2.setOnClickListener {
            room.clear_sps(title)
            bind.save1.visibility = View.VISIBLE
            bind.save2.visibility = View.GONE
        }



        reload_comments()


        bind.spinKit.visibility = View.GONE

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
                if (it.status){
                    Toastmessage("comment added")
                    reload_comments()
                }
            })

        }

    }

    private fun reload_comments(){
        val call = ViewModelProvider(this@ViewActivity_post)[ViewModel_comments::class.java]
        call.call(this)
        call.response.observe(this@ViewActivity_post, Observer {
            bind.recyclerView2.apply {
                this.adapter = RecyclerAdapter_comments(it)
                this.layoutManager = LinearLayoutManager(this@ViewActivity_post)
            }
        })
    }
}