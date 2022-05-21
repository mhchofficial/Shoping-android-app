package com.example.test1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.ViewActivity_post
import com.example.test1.apiConfig.models.comments.Result
import com.example.test1.databinding.CommentRecyclerBinding
import com.example.test1.databinding.SaveitemsRecyclerBinding
import com.example.test1.db.RoomModel_save
import org.w3c.dom.Comment

class RecyclerAdapter_saved(val model: List<RoomModel_save>) : RecyclerView.Adapter<RecyclerAdapter_saved.viewholder>() {
    class viewholder(val itemsbinding: SaveitemsRecyclerBinding): RecyclerView.ViewHolder(itemsbinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.saveitems_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.itemsbinding.model = model[position]
        val context = holder.itemsbinding.body.context
        holder.itemsbinding.parrentCard.setOnClickListener {
            val i = Intent(context, ViewActivity_post::class.java)
            i.putExtra("title", model[position].title)
            i.putExtra("body", model[position].body)
            i.putExtra("img", model[position].img)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return model.size
    }


}