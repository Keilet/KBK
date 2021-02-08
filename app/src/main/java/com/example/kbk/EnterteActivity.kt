package com.example.kbk

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterte.*


class EnterteActivity : AppCompatActivity(), View.OnClickListener {

    val inlogin: TextInputEditText = findViewById(R.id.login)
    val inpass: TextInputEditText = findViewById(R.id.pass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterte)



        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        entebutton.setOnClickListener(this)
    }

    fun userSignUp()
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
        TODO("Not yet implemented")
        userSignUp()
    }
}