package com.example.kbk.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kbk.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterst.*


class EnterstActivity : AppCompatActivity(),View.OnClickListener{

    lateinit var inlogin: TextInputEditText
    lateinit var inpass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterst)
        inlogin = findViewById(R.id.login)
        inpass= findViewById(R.id.pass)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        enstbutton.setOnClickListener(this)
    }
    fun userSignIn()
    {
        val login: String = inlogin.text.toString().trim()
        val pass: String = inpass.text.toString().trim()

        if (login.isEmpty())
        {
            inlogin.setError("Необходим логин")
            inlogin.requestFocus()
            return
        }

        if (pass.isEmpty())
        {

            inpass.setError("Необходим пароль")
            inpass.requestFocus()
            return
        }
    }
    override fun onClick(v: View?) {
        userSignIn()
    }
}