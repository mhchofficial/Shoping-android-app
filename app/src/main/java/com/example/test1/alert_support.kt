package com.example.test1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi


class alert_support(context: Context): AlertDialog(context) {
    @SuppressLint("ResourceType")



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_popup)
        this.window?.attributes?.windowAnimations = R.style.DialogAnimation
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        this.setCancelable(false)
        this.window?.setGravity(Gravity.CENTER)

        this.findViewById<ImageView>(R.id.close).setOnClickListener {
            this.dismiss()
        }


    }
}