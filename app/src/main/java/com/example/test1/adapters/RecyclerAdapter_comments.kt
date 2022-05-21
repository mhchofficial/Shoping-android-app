package com.example.test1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.apiConfig.models.comments.Result
import com.example.test1.databinding.CommentRecyclerBinding

class RecyclerAdapter_comments(val model: List<Result>) : RecyclerView.Adapter<RecyclerAdapter_comments.viewholder>() {
    class viewholder(val itemsbinding: CommentRecyclerBinding): RecyclerView.ViewHolder(itemsbinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.itemsbinding.model = model[position]
        val img = holder.itemsbinding.imgC
        Glide.with(img.context).load(model[position].pic_url).into(img)



    }

    override fun getItemCount(): Int {
        return model.size
    }


}