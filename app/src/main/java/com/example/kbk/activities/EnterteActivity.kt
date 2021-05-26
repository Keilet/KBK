package com.example.kbk.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.example.kbk.*
import com.example.kbk.model.LoginResponse
import com.example.kbk.network.Api
import com.example.kbk.network.Constants
import com.example.kbk.network.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterte.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EnterteActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var inlogin: TextInputEditText
    lateinit var inpass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterte)
        inlogin = findViewById(R.id.login)
        inpass = findViewById(R.id.pass)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        entebutton.setOnClickListener(this)
    }

    fun userSignIn() {
        val login: String = inlogin.text.toString().trim()
        val pass: String = inpass.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        if (login.isEmpty()) {
            inlogin.setError("Необходим логин")
            inlogin.requestFocus()
            return
        }

        if (pass.isEmpty()) {
            inpass.setError("Необходим пароль")
            inpass.requestFocus()
            return
        }

        var call: Call<LoginResponse> = service.userlogin(login, pass)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (!response.body()!!.error) {
                    finish()
                    val settings:SharedPreferences  = getSharedPreferences("Account", MODE_PRIVATE)
                    var idu:SharedPreferences.Editor=settings.edit()
                    idu.putInt("idu",response.body()!!.user.id)
                    idu.apply()
                    startActivity(Intent(applicationContext, Bnv::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Неправильный логин или пароль",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })


    }

    override fun onClick(v: View?) {
        userSignIn()
    }

    override fun onStart() {
        super.onStart()
        val settings: SharedPreferences =
            this!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
        val idu: Int = settings.getInt("idu", 0)
        if(idu!=0) startActivity(Intent(applicationContext, Bnv::class.java))
    }
}