package com.example.kbk.ui.menu

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.menu_documents.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Documents:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_documents)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

    }
}