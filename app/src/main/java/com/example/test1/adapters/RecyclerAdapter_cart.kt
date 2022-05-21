package com.example.test1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.apiConfig.models.comments.Result
import com.example.test1.databinding.CartRecyclerBinding
import com.example.test1.databinding.CommentRecyclerBinding
import com.example.test1.db.RoomModel_cart
import org.w3c.dom.Comment

class RecyclerAdapter_cart(val model: List<RoomModel_cart>) : RecyclerView.Adapter<RecyclerAdapter_cart.viewholder>() {
    class viewholder(val itemsbinding: CartRecyclerBinding): RecyclerView.ViewHolder(itemsbinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cart_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.itemsbinding.model = model[position]


        //here we can set a intent to open web page and client pay the price



    }

    override fun getItemCount(): Int {
        return model.size
    }


}