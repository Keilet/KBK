package com.example.kbk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tebutton.setOnClickListener {
            val intent = Intent(this,EnterteActivity::class.java)
            startActivity(intent)
        }
        stbutton.setOnClickListener {
            val intent = Intent(this,EnterstActivity::class.java)
            startActivity(intent)
        }
        abbutton.setOnClickListener {
            val intent = Intent(this,Bnv::class.java)
            startActivity(intent)
        }
    }


}