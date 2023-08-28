package com.sumita.newapp.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumita.newapp.Adapter.NewsAdapter
import com.sumita.newapp.ApiResponse.News
import com.sumita.newapp.apicall.NewsSeversis
import com.sumita.newapp.R
import kotlinx.android.synthetic.main.fragment_recycle.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Recycle : Fragment() {
    lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_recycle, container, false)
        getNews()
        return view
    }
    private fun getNews() {
        val news : Call<News> = NewsSeversis.newsIntence.getHeadlines("in",1)
        //retrofit use a queue and store all the request on that queue and process one by one.when one
        // request processed it its callback and then process another
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                //here response is a News response
                val news: News?=response.body()
                if (news!=null){
                    Log.d("response",news.toString())
                    adapter= NewsAdapter(news.articles)

                    newsList.adapter=adapter
                    newsList.layoutManager= LinearLayoutManager(requireContext())
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("response","error occur",t)
            }
        })
    }
}
