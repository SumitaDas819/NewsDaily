package com.sumita.newapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sumita.newapp.ApiResponse.Artical
import com.sumita.newapp.R
import com.sumita.newapp.fragments.DetailsNews


class NewsAdapter (val artical: List<Artical>):RecyclerView.Adapter<NewsAdapter.articalViewHolder>(){
   inner class articalViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articalViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return articalViewHolder(view)
    }

    override fun onBindViewHolder(holder: articalViewHolder, position: Int) {
        val articals: Artical =artical[position]
        holder.itemView.apply {
            newsTitle.text=articals.title
            newsDescription.text=articals.description
            Glide.with(context).load(articals.urlToImage).into(newsImage)
            holder.itemView.setOnClickListener {
                val fragment = DetailsNews.newInstance(articals.url)
                val fragmentManager = (context as AppCompatActivity).supportFragmentManager
                fragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    override fun getItemCount(): Int {
       return artical.size
    }
}