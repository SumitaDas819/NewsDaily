package com.sumita.newapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumita.newapp.R
import com.sumita.newapp.fragments.Recycle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclef= Recycle()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container,recyclef)
            commit()
        }
    }
}