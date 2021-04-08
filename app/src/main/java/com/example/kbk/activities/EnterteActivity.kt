package com.example.kbk.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kbk.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterte.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val TAG = "EnterteActivity"

class EnterteActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var inlogin: TextInputEditText
    lateinit var inpass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enterte)
        inlogin = findViewById(R.id.login)
        inpass= findViewById(R.id.pass)



        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        entebutton.setOnClickListener(this)
    }

    fun userSignIn()
    {
        val login: String = inlogin.text.toString().trim()
        val pass: String = inpass.text.toString().trim()

/*        val retrofit=Retrofit.Builder()
                .baseUrl(ServiceBuilder.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service:Api = retrofit.create(Api::class.java)*/


        //val call: Call<LoginResponse> = RetrofitClient.service.userlogin(login, pass)
        val call: Call<LoginResponse> = service.fetchContents()



        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
               //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.e(TAG, "Response received:${response.body()}")
                //progressDialog.dismiss()
//                if (!response.body().error()) {
//                    finish()
//                    SharedPrefManager.getInstance(applicationContext).userLogin(response.body().getUser())
//                    startActivity(Intent(applicationContext, HomeActivity::class.java))
//                } else {
//                    Toast.makeText(applicationContext, "Invalid email or password", Toast.LENGTH_LONG).show()
//                }
            }


        })


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