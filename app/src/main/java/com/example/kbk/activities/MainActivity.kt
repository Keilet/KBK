package com.example.kbk.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kbk.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tebutton.setOnClickListener {
            val intent = Intent(this, EnterteActivity::class.java)
            startActivity(intent)
        }
        stbutton.setOnClickListener {
            val intent = Intent(this, EnterstActivity::class.java)
            startActivity(intent)
        }
        abbutton.setOnClickListener {
            val intent = Intent(this, Bnv::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        val settings: SharedPreferences =
            this!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
        var id_searchgroup: SharedPreferences.Editor=settings.edit()
        id_searchgroup.putInt("id_searchgroup",0)
        id_searchgroup.apply()
    }


}