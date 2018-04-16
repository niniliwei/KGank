package com.niniliwei.kgank

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.niniliwei.kgank.api.ResponseBean
import com.niniliwei.kgank.glide.GlideApp
import kotlinx.android.synthetic.main.main_recycler_item.view.*

class MainAdapter(mainActivity: MainActivity): RecyclerView.Adapter<MainViewHolder>() {

    private val data = arrayListOf<ResponseBean>()
    private val glideRequests = GlideApp.with(mainActivity)

    fun setNewData(newData: List<ResponseBean>) {
        with(data) {
            clear()
            addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(R.layout.main_recycler_item, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val bean = data[position]
        glideRequests.load(bean.url)
                .centerCrop()
                .transition(crossFade)
                .into(holder.itemView.image_view)
    }
}

val crossFade = DrawableTransitionOptions.withCrossFade()

fun ResponseBean.thumbUrl(longEdge: Int) = "$url?imageView2/0/w/$longEdge"

class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)