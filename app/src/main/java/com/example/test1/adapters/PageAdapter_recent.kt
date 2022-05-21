package com.example.test1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.test1.apiConfig.models.recent.Result
import com.example.test1.R
import com.example.test1.ViewActivity_post
import com.example.test1.databinding.UpdatePagerBinding

class PageAdapter_recent(val model : List<Result>): PagerAdapter() {

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
        val bind: UpdatePagerBinding = DataBindingUtil.inflate(LayoutInflater.from(container.context)
            , R.layout.update_pager,container,false)


        //basically we must get id of post and send to activity and from activity call backend endpoint with post id and get fresh data from
        //server but im not a backend developer and cant do that (i can but im to tired) so we just send data from here to activity
        bind.title.text = model[position].title
        bind.body.text = model[position].body
        val context = container.context
        bind.recentSlider.setOnClickListener {
            val i = Intent(context, ViewActivity_post::class.java)
            i.putExtra("title", model[position].title)
            i.putExtra("body", model[position].body)
            i.putExtra("img", model[position].img_url)
            context.startActivity(i)
        }

        container.addView(bind.root)
        return bind.root
    }

    override fun getPageWidth(position: Int): Float {
        return 0.5f
    }

}