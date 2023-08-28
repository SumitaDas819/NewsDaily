package com.sumita.newapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sumita.newapp.R
import kotlinx.android.synthetic.main.fragment_details_news.*


class DetailsNews : Fragment() {
    // TODO: Rename and change types of parameters
    companion object {
        fun newInstance(url: String): DetailsNews {
            val fragment = DetailsNews()
            val bundle = Bundle()
            bundle.putString("EXTRA_URL", url)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_news, container, false)
        val webView = view.findViewById<WebView>(R.id.detailsWebview)
        val url = arguments?.getString("EXTRA_URL")
        // Use the URL as needed
        if (url !=null){
            Log.d("DataFlow", "URL received: $url")
            webView.settings.javaScriptEnabled=true
            webView.settings.userAgentString="Mozilla/5.0 (Linux; Android 9; CPH1909) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.82 Mobile Safari/537.36"
            /* .settings is a property of the WebView object that provides
             access to the settings of the WebView. By accessing the settings
             property, you can configure various properties and behaviors of the WebView.*/
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    progressBar.visibility=View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView?.loadUrl(url)
        }else  Log.d("DataFlow", "No URL received")

        return view

    }


}
