package com.example.test1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.test1.apiConfig.models.pro.Result
import com.example.test1.R
import com.example.test1.ViewActivity_shop
import com.example.test1.databinding.BottomPagerBinding

class PageAdapter_pru(val model : List<Result>): PagerAdapter() {

    override fun getCount(): Int {
        return model.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view==`object`


    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as View)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val bind: BottomPagerBinding = DataBindingUtil.inflate(LayoutInflater.from(container.context)
            , R.layout.bottom_pager,container,false)
        bind.image = model[position].img_url

        //basically we must get id of post and send to activity and from activity call backend endpoint with post id and get fresh data from
        //server but im not a backend developer and cant do that (i can but im to tired) so we just send data from here to activity
        bind.title.text = model[position].title
        bind.price.text = model[position].price.toString()



        val context = container.context
        bind.bottomSlider.setOnClickListener {
            val i = Intent(context, ViewActivity_shop::class.java)
            i.putExtra("title", model[position].title)
            i.putExtra("price", model[position].price.toString())
            i.putExtra("img", model[position].img_url)
            context.startActivity(i)
        }

        container.addView(bind.root)
        return bind.root
    }

    override fun getPageWidth(position: Int): Float {
        return 0.4f
    }

   companion object {
       @JvmStatic
       @BindingAdapter("image")
       fun ImageViewPost(view: ImageView, url: String){
           Glide.with(view).load(url).into(view)
       }
   }
}