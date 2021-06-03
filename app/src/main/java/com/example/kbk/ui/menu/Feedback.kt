package com.example.kbk.ui.menu

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kbk.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterst.*
import kotlinx.android.synthetic.main.menu_feedback.*

class Feedback: AppCompatActivity(),View.OnClickListener {

    lateinit var usname: TextInputEditText
    lateinit var usphone: TextInputEditText
    lateinit var usquestion: TextInputEditText
    lateinit var ustime: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_feedback)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        ussend.setOnClickListener(this)

    }
    fun sendForm() {
        usname = findViewById(R.id.usname)
        usphone = findViewById(R.id.usphone)
        usquestion = findViewById(R.id.usquestion)
        ustime = findViewById(R.id.ustime)
        val uname: String = usname.text.toString().trim()
        val uphone: String = usphone.text.toString().trim()
        val uquestion: String = usquestion.text.toString().trim()
        val utime: String = ustime.text.toString().trim()

        if (uname.isEmpty()) {
            usname.setError("Введите свое имя")
            usname.requestFocus()
            return
        }

        if (uphone.isEmpty()) {
            usphone.setError("Введите номер телефона")
            usphone.requestFocus()
            return
        }
        if (uquestion.isEmpty()) {
            usquestion.setError("Введите свое имя")
            usquestion.requestFocus()
            return
        }

        if (utime.isEmpty()) {
            ustime.setError("Введите номер телефона")
            ustime.requestFocus()
            return
        }
    }

    override fun onClick(v: View?) {
        sendForm()
    }
}